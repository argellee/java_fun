package io;

import java.io.File;
import java.io.FileNotFoundException;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Scanner;  
  
public class CSVUtil {  
      
    /** 
     * 解析csv文件，并将其转换成sql语句 
     * @param csv 
     * @return 
     * @throws FileNotFoundException  
     */  
    public static List<String> csv2sql(String csv) throws FileNotFoundException {  
  
        //存储sql语句  
        List<String> sqls = new ArrayList<String>();  
          
        //取得csv文件名称，csv文件名称，即表名称  
        File csvfile = new File(csv);  
        String table_name = csvfile.getName();  
          
        //去除文件后缀  
        if (table_name.contains(".")) {  
            table_name = table_name.substring(0, table_name.indexOf("."));  
        }  
          
        //扫描器，用于逐行变量csv文件内容（该类自jdk5开始提供）  
        //需正确指定编码，否则有中文时，不能正常读取。  
        Scanner scanner = new Scanner(csvfile,"GBK");
          
        //设置扫描器的分隔符，如不设置，默认使用空格进行分割。  
        //此处设置分隔符为“折行符”,即一次读一行。  
        scanner.useDelimiter("\\n");  
  
        //列名数组  
        String column_names[] = null;  
        int column_count = 0;  
          
        //每条sql前半部分是相同的。  
        StringBuilder header = new StringBuilder("insert into " + table_name + " (");  
  
        //先取第一行，第一行是列名称  
        if(scanner.hasNext()){  
            String first_line = scanner.next();  
            column_names = first_line.split(",");  
            column_count = column_names.length;  
            for (int j = 0; j < column_names.length; j++) {  
                header.append(column_names[j].trim()).append(",");  
            }  
            header.deleteCharAt(header.length() - 1);  
            header.append(")");  
          
        }  
        //逐行扫描下面的数据行  
        while (scanner.hasNext()) {  
            StringBuilder sql = new StringBuilder(header);  
            String line = scanner.next();  
            String values[] = line.split(",");  
              
            // 当前行数据中不包含逗号（分隔符除外）的情况，该情况下处理简单  
            if (column_count == values.length) {  
                sql.append(" values(");  
                for (int j = 0; j < values.length; j++) {  
                    String value = values[j];  
                      
                    // 每行最后一个元素，去除末端换行符(\r\n)  
                    if (j == column_count - 1) {  
                        value = value.replaceAll("\r|\n", "");  
                    }  
                    sql.append(value);  
                    sql.append(",");  
                }  
                sql.deleteCharAt(sql.length() - 1);  
                sql.append(")");  
            } else {  
                // 当前行数据自身也包含逗号的场景  
                sql.append(" values(");  
                for (int j = 0; j < values.length; j++) {  
                    String true_value = null;  
                    String value = values[j];  
                      
                    // 最后一个元素，去除末端换行符(\r\n)  
                    if (j == values.length - 1) {  
                        value = value.replaceAll("\r|\n", "");  
                    }  
                      
                    //如果当前元素的值以双引号打头，需要进一步细化处理  
                    if (value.startsWith("\"")) {  
                          
                        //如果当前元素的值也以"结尾，但不以\"结尾，则该元素的值可认为是完整的值。  
                        if (value.endsWith("\"") && !value.endsWith("\\\"")) {  
                            true_value = value;  
                        } else {  
                            //否则...  
                            while (true) {  
                                j++;  
                                if (j == values.length) {  
                                    break;  
                                }  
                                value = value + "," + values[j];  
                                  
                                // 最后一个元素，去除末端换行符(\r\n)  
                                if (j == values.length - 1) {  
                                    value = value.replaceAll("\r|\n", "");  
                                }  
                                  
                                if (values[j].endsWith("\"") && !value.endsWith("\\\"")) {  
                                    true_value = value;  
                                    break;  
                                }  
                            }  
                        }  
                    } else {  
                        //如果当前元素的值不以双引号打头，则该元素的值一定是完整的值。  
                        true_value = value;  
                    }  
                    sql.append(true_value);  
                    sql.append(",");  
                }  
                sql.deleteCharAt(sql.length() - 1);  
                sql.append(")");  
            }  
            sqls.add(sql.toString());  
        }  
        // 关闭扫描器  
        if (scanner != null) {  
            scanner.close();  
        }  
        return sqls;  
    }  
      
    public static void main(String[] args) throws FileNotFoundException {  
          
        String path = "C:\\Users\\lijin9\\Desktop\\data.csv";
        List<String> sqls = csv2sql(path);  
        for(String sql : sqls){  
            System.out.println(sql);      
        }  
    }  
}  