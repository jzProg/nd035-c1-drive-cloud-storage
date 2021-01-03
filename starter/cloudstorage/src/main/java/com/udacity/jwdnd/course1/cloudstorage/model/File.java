package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] fileData;

    public File(Integer fileId, String filename, String contenttype, String filesize, Integer userid, byte[] fileData) {
        this.fileId = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.fileData = fileData;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
