package com.trashbin;

import com.trashbin.domain.Address;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.domain.TrashCategory;
import com.trashbin.repository.TrashBinRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final TrashBinRepository trashBinRepository;


    @Transactional
    public void readExcel() throws IOException {
        FileInputStream file = new FileInputStream("src/main/resources/excel/쓰레기통 위도경도 변환 완료.xlsx");
        Workbook workbook = new XSSFWorkbook(file);

        // Next, let's retrieve the first sheet of the file and iterate through each row:
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING: {
                        if(cell.getRichStringCellValue().getString().equals("error")) continue;
                        data.get(i).add(cell.getRichStringCellValue().getString());
                        break;
                    }
                    case NUMERIC: {
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i).add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i).add(cell.getNumericCellValue() + "");
                        }
                        break;
                    }
                    case BOOLEAN: {
                        data.get(i).add(cell.getBooleanCellValue() + "");
                        break;
                    }
                    case FORMULA: {
                        data.get(i).add(cell.getCellFormula() + "");
                        break;
                    }
                    default:
                        data.get(i).add(" ");
                }
            }
            i++;
        }

        i=0;
        for (List<String> d: data.values()) {

            System.out.println(i+" "+d);
            if(d.get(0).equals("자치구명")) continue;

            String category = d.get(4);

            TrashCategory trashCategory;
            if (category.equals("담배꽁초")) trashCategory = TrashCategory.CIGARETTE;
            else if (category.equals("재활용")) trashCategory = TrashCategory.RECYCLE;
            else trashCategory = TrashCategory.GENERAL;

            trashBinRepository.save(TrashBinEntity.builder()
                    .trashCategory(trashCategory)
                    .address(Address.builder()
                            .gu(d.get(0))
                            .roadName(d.get(1))
                            .detailAddress(d.get(2))
                            .installPoint(d.get(3))
                            .latitude(Double.parseDouble(d.get(5)))
                            .longitude(Double.parseDouble(d.get(6)))
                            .build())
                    .build());
            i++;
        }
    }
}
