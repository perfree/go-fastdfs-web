package com.perfree.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.DateUtil;
import com.perfree.common.FileSizeUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @author Perfree
 * @description 首页相关逻辑处理
 * @date 2021/4/7 8:42
 */
@Service
public class IndexService {

    /**
     * @description 获取状态
     * @param data data
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author Perfree
     */
    public Map<String, Object> getStatus(Object data) {
        // 声明结果集
        Map<String, Object> result = new HashMap<>();
        // 声明30天文件大小数据容器
        List<String> dayFileSizeList = new ArrayList<>();
        // 声明30天文件数量数据容器
        List<String> dayFileCountList = new ArrayList<>();
        // 声明30天内日期容器
        List<String> dayNumList = new ArrayList<>();
        JSONObject parseObj = JSONUtil.parseObj(data);
        JSONArray parseArray = JSONUtil.parseArray(parseObj.get("Fs.FileStats"));
        // 剩余空间
        result.put("diskFreeSize", FileSizeUtil.GetLength(Long.parseLong(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("free" ,"0"))));
        // 总空间
        result.put("diskTotalSize", FileSizeUtil.GetLength(Long.parseLong(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("total","0"))));
        // 已使用空间
        result.put("diskUsedSize", FileSizeUtil.GetLength(Long.parseLong(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("used","0"))));
        // 节点
        result.put("inodesTotal", JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesTotal","0"));
        // 节点
        result.put("inodesUsed", JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesUsed","0"));
        // 节点
        result.put("inodesFree", JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesFree","0"));
        long dayFileSize = 0;
        long dayFileCount = 0;
        for (int i = 0; i < parseArray.size(); i++) {
            JSONObject fileStats = JSONUtil.parseObj(parseArray.getStr(i));
            if (fileStats.get("date").equals("all")) {
                // 获取总文件大小,数量
                result.put("totalFileSize", FileSizeUtil.GetLength(Long.parseLong(fileStats.getStr("totalSize","0"))));
                result.put("totalFileCount", fileStats.getStr("fileCount","0"));
            } else {
                try {
                    long subDay = DateUtil.daysBetween(DateUtil.StrToDate(fileStats.getStr("date"), "yyyyMMdd"), new Date(), false);
                    if (subDay <= 30) {
                        // 获取30天内文件大小,数量
                        dayFileSize += Long.parseLong(fileStats.getStr("totalSize","0"));
                        dayFileCount += Long.parseLong(fileStats.getStr("fileCount","0"));
                        dayFileSizeList.add(FileSizeUtil.GetMBLength(Long.parseLong(fileStats.getStr("totalSize","0"))));
                        dayFileCountList.add(fileStats.getStr("fileCount","0"));
                        dayNumList.add(fileStats.getStr("date").substring(6) + "日");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        result.put("dayFileSize", FileSizeUtil.GetLength(dayFileSize));
        result.put("dayFileCount", dayFileCount);
        result.put("dayFileSizeList", dayFileSizeList);
        result.put("dayFileCountList", dayFileCountList);
        result.put("dayNumList", dayNumList);
        return result;
    }
}
