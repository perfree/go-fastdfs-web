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
 * 首页逻辑处理
 * @author Perfree
 */
@Service
public class IndexService {

    /**
     * 文件存储统计
     * @param data
     * @return Map<String, Object>
     */
    public Map<String, Object> getfileStat(Object data) {
        //声明结果集
        Map<String,Object> result = new HashMap<>();
        //声明30天文件大小数据容器
        List<String> dayFileSizeList = new ArrayList<>();
        //声明30天文件数量数据容器
        List<String> dayFileCountList = new ArrayList<>();
        //声明30天内日期容器
        List<String> dayNumList = new ArrayList<>();
        JSONObject parseObj = JSONUtil.parseObj(data);
        JSONArray parseArray = JSONUtil.parseArray(parseObj.get("Fs.FileStats"));
        // 剩余空间
        result.put("diskFreeSize",FileSizeUtil.GetLength(Long.valueOf(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("free"))));
        // 总空间
        result.put("diskTotalSize",FileSizeUtil.GetLength(Long.valueOf(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("total"))));
        // 已使用空间
        result.put("diskUsedSize",FileSizeUtil.GetLength(Long.valueOf(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("used"))));
        // 节点
        result.put("inodesTotal",Long.valueOf(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesTotal")));
        // 节点
        result.put("inodesUsed",Long.valueOf(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesUsed")));
        // 节点
        result.put("inodesFree",Long.valueOf(JSONUtil.parseObj(parseObj.get("Sys.DiskInfo")).getStr("inodesFree")));
        long dayFileSize = 0;
        long dayFileCount = 0;
        for (int i = 0;i < parseArray.size();i++) {
            JSONObject fileStats = JSONUtil.parseObj(parseArray.getStr(i));
            if(fileStats.get("date").equals("all")) {
                //获取总文件大小,数量
                result.put("totalFileSize",FileSizeUtil.GetLength(Long.valueOf(fileStats.getStr("totalSize"))));
                result.put("totalFileCount",fileStats.getStr("fileCount"));
            }else {
                try {
                    long subDay = DateUtil.daysBetween(DateUtil.StrToDate(fileStats.getStr("date"), "yyyyMMdd"), new Date(), false);
                    if (subDay <= 30){
                        //获取30天内文件大小,数量
                        dayFileSize += Long.valueOf(fileStats.getStr("totalSize"));
                        dayFileCount += Long.valueOf(fileStats.getStr("fileCount"));
                        dayFileSizeList.add(FileSizeUtil.GetMBLength(Long.valueOf(fileStats.getStr("totalSize"))));
                        dayFileCountList.add(fileStats.getStr("fileCount"));
                        dayNumList.add(fileStats.getStr("date").substring(6)+"日");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        result.put("dayFileSize",FileSizeUtil.GetLength(dayFileSize));
        result.put("dayFileCount",dayFileCount);
        result.put("dayFileSizeList",dayFileSizeList);
        result.put("dayFileCountList",dayFileCountList);
        result.put("dayNumList",dayNumList);
        return result;
    }
}
