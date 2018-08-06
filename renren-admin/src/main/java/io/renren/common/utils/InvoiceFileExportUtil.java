package io.renren.common.utils;

import io.renren.modules.invoice.entity.InvoCheckDetlEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by sheying on 2018/07/30.
 */
public class InvoiceFileExportUtil {

    // 活动数据，拼团商品数据
    private static final String[] INVOICE_EXCEL_HEAD = {"发票代码", "发票号码", "录入日期"};

    public static HSSFWorkbook generateHSSFWorkbook( List<InvoCheckDetlEntity> entityList){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("发票信息");

        writeSheet(sheet1, entityList, INVOICE_EXCEL_HEAD);

        return wb;
    }

    private static <T> void writeSheet(HSSFSheet sheet, List<T> dailies, String[] titles) {
        for (int rowNumber = 0; rowNumber < dailies.size() + 1; rowNumber++) {
            HSSFRow row = sheet.createRow(rowNumber);
            for (int columnNumber = 0; columnNumber < titles.length; columnNumber++) {
                HSSFCell cell = row.createCell(columnNumber);
                if(rowNumber == 0){
                    sheet.createFreezePane(0,1,0,1);
                    cell.setCellValue(titles[columnNumber]);
                }else{
                    writeCell(dailies.get(rowNumber - 1), columnNumber, cell);
                }
            }
        }
    }

    private static <T> void writeCell(T daily, int columnNumber, HSSFCell cell) {
        if(daily instanceof InvoCheckDetlEntity){
            cell.setCellValue(getValue((InvoCheckDetlEntity) daily, columnNumber));
        }
    }

    private static String getValue(InvoCheckDetlEntity daily, int j) {
        switch (j) {
            case 0: return daily.getInvoiceDataCode(); // 发票代码
            case 1: return daily.getInvoiceNumber();   // 发票号码
            case 2: return daily.getCrtDt(); // 录入日期
        }
        return "";
    }

}
