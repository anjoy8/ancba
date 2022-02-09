package club.neters.common.util.excel;

import club.neters.common.util.ResultGenerator;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelEasyService  {
    public void tempExportDownload(HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = "insurance-bill-template.xlsx";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName);
        InputStream io = null;
        ServletOutputStream out = null;
        try {
            io = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            out = response.getOutputStream();
            if (io != null) {
                IOUtils.copy(io, out);
            }
        } catch (Exception e) {
            throw new RuntimeException("下载文件失败，失败原因：下载文件异常！");
        } finally {
            IOUtils.closeQuietly(io);
            IOUtils.closeQuietly(out);
        }
    }

    public ResultExcel importHandler(InputStream inp, List<ExcelDemoVo> sucList, List<ExcelDemoVo> badList, String tenantId) throws Exception {
        try {
            if (inp == null) {
                return ResultGenerator.genFailResult("excel格式异常");
            }

            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(0);

            ResultExcel resultCheck = checkHead(row);
            if (resultCheck.getCode() != 200) {
                return resultCheck;
            }

            Set<String> policyNumberSet = new HashSet<>();

            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) {
                ExcelDemoVo data = new ExcelDemoVo();
                Row r = sheet.getRow(i);
                String policyNumber= ExcelUtil.getCellValue(r.getCell(0));
                data.setPolicyNumber(policyNumber);
                String insurerName = ExcelUtil.getCellValue(r.getCell(1));
                data.setInsurerName(insurerName);
                String deal = ExcelUtil.getCellValue(r.getCell(2));
                data.setDeal(deal);
                String vin = ExcelUtil.getCellValue(r.getCell(3));
                data.setVin(vin);
                String plateNumber = ExcelUtil.getCellValue(r.getCell(4));
                data.setPlateNumber(plateNumber);
                String inceptionDate = ExcelUtil.getDateCellValue(r.getCell(5));
                data.setInceptionDate(inceptionDate);

                if (StringUtils.isEmpty(policyNumber)) {
                    data.setError("第" + (i + 1) + "行缺少policyNumber");
                    badList.add(data);
                    continue;
                }
                // policyNumber
                if (policyNumberSet.contains(policyNumber)) {
                    data.setError("第" + (i + 1) + "行policyNumber重复");
                    badList.add(data);
                    continue;
                } else {
                    policyNumberSet.add(policyNumber);
                }

                if (StringUtils.isEmpty(insurerName)) {
                    data.setError("第" + (i + 1) + "行缺少insurerName");
                    badList.add(data);
                    continue;
                }
                if (StringUtils.isEmpty(deal)) {
                    data.setError("第" + (i + 1) + "行缺少deal");
                    badList.add(data);
                    continue;
                }
                if (StringUtils.isEmpty(plateNumber)) {
                    data.setError("第" + (i + 1) + "行缺少plateNumber");
                    badList.add(data);
                    continue;
                }
                if (StringUtils.isEmpty(inceptionDate)) {
                    data.setError("第" + (i + 1) + "行缺少inceptionDate");
                    badList.add(data);
                    continue;
                }
                if (!isDate(inceptionDate)) {
                    data.setError("第" + (i + 1) + "行inceptionDate不符合yyyy-MM-dd格式");
                    badList.add(data);
                    continue;
                }
                sucList.add(data);
            }
        } catch (IOException e) {
            return ResultGenerator.genFailResult("excel格式异常");
        }
        return ResultGenerator.genSuccessResult("ok");
    }

    public ResultExcel exportHandler(HttpServletResponse response, List<ExcelDemoVo> list)   {
        if (!CollectionUtils.isEmpty(list)) {
            ExcelData<ExcelDemoVo> data = new ExcelData<>();
            data.setFileName("Insurances");
            data.setData(list);
            exportDataInExcel(response, data, ExcelDemoVo.class);
            return ResultGenerator.genSuccessResult("ok");
        } else {
            return ResultGenerator.genFailResult("数据不存在");
        }
    }

    private static ResultExcel checkHead(Row row) {
        if (row == null) {
            return ResultGenerator.genFailResult("excel格式异常");
        }

        List<String> cellHeads = Arrays.asList("PolicyNumber", "InsurerName", "Deal", "VIN", "PlateNumber", "InceptionDate");

        for (int i = 0; i < cellHeads.size(); i++) {
            Cell c = row.getCell(i);
            String headeName = cellHeads.get(i);
            String regionHead = c.getStringCellValue();
            if (!headeName.equals(regionHead)) {
                return ResultGenerator.genFailResult("第1行第" + (i + 1) + "列单元格内容必须为" + headeName);
            }
        }

        if (cellHeads.size() != row.getPhysicalNumberOfCells()) {
            return ResultGenerator.genFailResult("excel列数不匹配");
        }

        return ResultGenerator.genSuccessResult("ok");
    }

    private static boolean isNumber(String age) {
        try {
            Integer.parseInt(age);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    public <T> void exportDataInExcel(HttpServletResponse response, ExcelData<?> data, Class<T> clazz) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + data.getFileName() + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), clazz)
                    .autoCloseStream(Boolean.FALSE)
                    // 列自适应宽度
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(data.getFileName())
                    .doWrite(data.getData());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>();
            map.put("status", "failure");
            map.put("message", "下载文件失败，失败原因：" + e.getMessage());
//            try {
//                response.getWriter().println(JSON.toJSONString(map));
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
        }
    }
}
