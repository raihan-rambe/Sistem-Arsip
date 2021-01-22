package com.example.rensposi.Model;

public class HistoryModel {

    private String mIdcek;
    private String mTanggal;
    private String mRiwayat;
    private String mTotal;
    private int mImageResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public HistoryModel(String idBook, String tanggal, String riwayat, String total, int imageResourceId) {
        mIdcek = idBook;
        mTanggal = tanggal;
        mRiwayat = riwayat;
        mTotal = total;
        mImageResourceId = imageResourceId;
    }

    public String getIdBook() {
        return mIdcek;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public String getRiwayat() {
        return mRiwayat;
    }

    public String getTotal() {
        return mTotal;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}