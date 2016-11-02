package com.example.iem.apptub.classes;

/**
 * Created by iem on 02/11/2016.
 */

public class Folder {
    private String mName;
    private int mIsFavorite;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.mIsFavorite = isFavorite;
    }

    String mId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }


//    public boolean equals(Object o) {
//        // Same memory address, equal
//        if (this == o) return true;
//        //Null object or different classes, not equal
//        if (o == null || getClass() != o.getClass()) return false;
//        // If same ids, equal,
//        // Else, not equal
//        return ((Model) o).getId() != null && ((Model) o).getId().equals(mId);
//    }
}
