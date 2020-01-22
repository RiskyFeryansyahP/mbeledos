package com.example.bledos.model;

public class PubnubModel {

    public class PNGCM {
        DataPubnubModel data;

        public PNGCM(DataPubnubModel data) {
            this.data = data;
        }
    }

    PNGCM pn_gcm;

    public void setPubnubModel(PNGCM pn_gcm) {
        this.pn_gcm = pn_gcm;
    }

    public PNGCM setPNGCM(DataPubnubModel data) {
        PNGCM pngcm = new PNGCM(data);
        return  pngcm;
    }
}
