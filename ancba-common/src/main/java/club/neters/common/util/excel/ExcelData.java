package club.neters.common.util.excel;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * excel 导出
 */
@ToString
@Data
@Accessors(chain = true)
public class ExcelData<T> {
    /**
     * excel 名称
     */
    private String fileName;
    /**
     * 导出的数据
     */
    private List<T> data;
}
