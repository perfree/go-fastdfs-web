package com.perfree.form;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @description GoFastDfs 配置文件对应实体
 * @author Perfree
 * @date 2021/3/23 15:56
 */
public class GoFastDfsCfg implements Serializable {
    private static final long serialVersionUID = 637655885822474661L;
    private String addr;
    private List<String> peers;
    @JSONField(name = "enable_https")
    private boolean enableHttps;
    private String group;
    @JSONField(name = "rename_file")
    private boolean renameFile;
    @JSONField(name = "show_dir")
    private boolean showDir;
    private List<String> extensions;
    @JSONField(name = "refresh_interval")
    private String refreshInterval;
    @JSONField(name = "enable_web_upload")
    private boolean enableWebUpload;
    @JSONField(name = "download_domain")
    private String downloadDomain;
    @JSONField(name = "enable_custom_path")
    private boolean enableCustomPath;
    private List<String> scenes;
    @JSONField(name = "alarm_receivers")
    private List<String> alarmReceivers;
    @JSONField(name = "default_scene")
    private String defaultScene;
    private JSONObject mail;
    @JSONField(name = "alarm_url")
    private String alarmUrl;
    @JSONField(name = "download_use_token")
    private boolean downloadUseToken;
    @JSONField(name = "download_token_expire")
    private Long downloadTokenExpire;
    @JSONField(name = "queue_size")
    private Long queueSize;
    @JSONField(name = "auto_repair")
    private boolean autoRepair;
    private String host;
    @JSONField(name = "file_sum_arithmetic")
    private String fileSumArithmetic;
    @JSONField(name = "peer_id")
    private String peerId;
    @JSONField(name = "support_group_manage")
    private boolean supportGroupManage;
    @JSONField(name = "admin_ips")
    private List<String> adminIps;
    @JSONField(name = "enable_merge_small_file")
    private boolean enableMergeSmallFile;
    @JSONField(name = "enable_migrate")
    private boolean enableMigrate;
    @JSONField(name = "enable_distinct_file")
    private boolean enableDistinctFile;
    @JSONField(name = "read_only")
    private boolean readOnly;
    @JSONField(name = "enable_cross_origin")
    private boolean enableCrossOrigin;
    @JSONField(name = "enable_google_auth")
    private boolean enableGoogleAuth;
    @JSONField(name = "auth_url")
    private String authUrl;
    @JSONField(name = "enable_download_auth")
    private boolean enableDownloadAuth;
    @JSONField(name = "default_download")
    private boolean defaultDownload;
    @JSONField(name = "enable_tus")
    private boolean enableTus;
    @JSONField(name = "sync_timeout")
    private Long syncTimeout;
    @JSONField(name = "enable_fsnotify")
    private boolean enableFsnotify;
    @JSONField(name = "enable_disk_cache")
    private boolean enableDiskCache;
    @JSONField(name = "connect_timeout")
    private boolean connectTimeout;
    @JSONField(name = "read_timeout")
    private Long readTimeout;
    @JSONField(name = "write_timeout")
    private Long writeTimeout;
    @JSONField(name = "idle_timeout")
    private Long idleTimeout;
    @JSONField(name = "read_header_timeout")
    private Long readHeaderTimeout;
    @JSONField(name = "sync_worker")
    private Long syncWorker;
    @JSONField(name = "upload_worker")
    private Long uploadWorker;
    @JSONField(name = "upload_queue_size")
    private Long uploadQueueSize;
    @JSONField(name = "retry_count")
    private Long retryCount;
    @JSONField(name = "sync_delay")
    private Long syncDelay;
    @JSONField(name = "watch_chan_size")
    private Long watchChanSize;


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<String> getPeers() {
        return peers;
    }

    public void setPeers(List<String> peers) {
        this.peers = peers;
    }

    public boolean isEnableHttps() {
        return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
        this.enableHttps = enableHttps;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isRenameFile() {
        return renameFile;
    }

    public void setRenameFile(boolean renameFile) {
        this.renameFile = renameFile;
    }

    public boolean isShowDir() {
        return showDir;
    }

    public void setShowDir(boolean showDir) {
        this.showDir = showDir;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

    public String getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(String refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public boolean isEnableWebUpload() {
        return enableWebUpload;
    }

    public void setEnableWebUpload(boolean enableWebUpload) {
        this.enableWebUpload = enableWebUpload;
    }

    public String getDownloadDomain() {
        return downloadDomain;
    }

    public void setDownloadDomain(String downloadDomain) {
        this.downloadDomain = downloadDomain;
    }

    public boolean isEnableCustomPath() {
        return enableCustomPath;
    }

    public void setEnableCustomPath(boolean enableCustomPath) {
        this.enableCustomPath = enableCustomPath;
    }

    public List<String> getScenes() {
        return scenes;
    }

    public void setScenes(List<String> scenes) {
        this.scenes = scenes;
    }

    public List<String> getAlarmReceivers() {
        return alarmReceivers;
    }

    public void setAlarmReceivers(List<String> alarmReceivers) {
        this.alarmReceivers = alarmReceivers;
    }

    public String getDefaultScene() {
        return defaultScene;
    }

    public void setDefaultScene(String defaultScene) {
        this.defaultScene = defaultScene;
    }

    public JSONObject getMail() {
        return mail;
    }

    public void setMail(JSONObject mail) {
        this.mail = mail;
    }

    public String getAlarmUrl() {
        return alarmUrl;
    }

    public void setAlarmUrl(String alarmUrl) {
        this.alarmUrl = alarmUrl;
    }

    public boolean isDownloadUseToken() {
        return downloadUseToken;
    }

    public void setDownloadUseToken(boolean downloadUseToken) {
        this.downloadUseToken = downloadUseToken;
    }

    public Long getDownloadTokenExpire() {
        return downloadTokenExpire;
    }

    public void setDownloadTokenExpire(Long downloadTokenExpire) {
        this.downloadTokenExpire = downloadTokenExpire;
    }

    public Long getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Long queueSize) {
        this.queueSize = queueSize;
    }

    public boolean isAutoRepair() {
        return autoRepair;
    }

    public void setAutoRepair(boolean autoRepair) {
        this.autoRepair = autoRepair;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFileSumArithmetic() {
        return fileSumArithmetic;
    }

    public void setFileSumArithmetic(String fileSumArithmetic) {
        this.fileSumArithmetic = fileSumArithmetic;
    }

    public String getPeerId() {
        return peerId;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }

    public boolean isSupportGroupManage() {
        return supportGroupManage;
    }

    public void setSupportGroupManage(boolean supportGroupManage) {
        this.supportGroupManage = supportGroupManage;
    }

    public List<String> getAdminIps() {
        return adminIps;
    }

    public void setAdminIps(List<String> adminIps) {
        this.adminIps = adminIps;
    }

    public boolean isEnableMergeSmallFile() {
        return enableMergeSmallFile;
    }

    public void setEnableMergeSmallFile(boolean enableMergeSmallFile) {
        this.enableMergeSmallFile = enableMergeSmallFile;
    }

    public boolean isEnableMigrate() {
        return enableMigrate;
    }

    public void setEnableMigrate(boolean enableMigrate) {
        this.enableMigrate = enableMigrate;
    }

    public boolean isEnableDistinctFile() {
        return enableDistinctFile;
    }

    public void setEnableDistinctFile(boolean enableDistinctFile) {
        this.enableDistinctFile = enableDistinctFile;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isEnableCrossOrigin() {
        return enableCrossOrigin;
    }

    public void setEnableCrossOrigin(boolean enableCrossOrigin) {
        this.enableCrossOrigin = enableCrossOrigin;
    }

    public boolean isEnableGoogleAuth() {
        return enableGoogleAuth;
    }

    public void setEnableGoogleAuth(boolean enableGoogleAuth) {
        this.enableGoogleAuth = enableGoogleAuth;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public boolean isEnableDownloadAuth() {
        return enableDownloadAuth;
    }

    public void setEnableDownloadAuth(boolean enableDownloadAuth) {
        this.enableDownloadAuth = enableDownloadAuth;
    }

    public boolean isDefaultDownload() {
        return defaultDownload;
    }

    public void setDefaultDownload(boolean defaultDownload) {
        this.defaultDownload = defaultDownload;
    }

    public boolean isEnableTus() {
        return enableTus;
    }

    public void setEnableTus(boolean enableTus) {
        this.enableTus = enableTus;
    }

    public Long getSyncTimeout() {
        return syncTimeout;
    }

    public void setSyncTimeout(Long syncTimeout) {
        this.syncTimeout = syncTimeout;
    }

    public boolean isEnableFsnotify() {
        return enableFsnotify;
    }

    public void setEnableFsnotify(boolean enableFsnotify) {
        this.enableFsnotify = enableFsnotify;
    }

    public boolean isEnableDiskCache() {
        return enableDiskCache;
    }

    public void setEnableDiskCache(boolean enableDiskCache) {
        this.enableDiskCache = enableDiskCache;
    }

    public boolean isConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(boolean connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public Long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(Long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public Long getReadHeaderTimeout() {
        return readHeaderTimeout;
    }

    public void setReadHeaderTimeout(Long readHeaderTimeout) {
        this.readHeaderTimeout = readHeaderTimeout;
    }

    public Long getSyncWorker() {
        return syncWorker;
    }

    public void setSyncWorker(Long syncWorker) {
        this.syncWorker = syncWorker;
    }

    public Long getUploadWorker() {
        return uploadWorker;
    }

    public void setUploadWorker(Long uploadWorker) {
        this.uploadWorker = uploadWorker;
    }

    public Long getUploadQueueSize() {
        return uploadQueueSize;
    }

    public void setUploadQueueSize(Long uploadQueueSize) {
        this.uploadQueueSize = uploadQueueSize;
    }

    public Long getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Long retryCount) {
        this.retryCount = retryCount;
    }

    public Long getSyncDelay() {
        return syncDelay;
    }

    public void setSyncDelay(Long syncDelay) {
        this.syncDelay = syncDelay;
    }

    public Long getWatchChanSize() {
        return watchChanSize;
    }

    public void setWatchChanSize(Long watchChanSize) {
        this.watchChanSize = watchChanSize;
    }
}
