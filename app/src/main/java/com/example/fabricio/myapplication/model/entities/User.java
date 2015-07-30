package com.example.fabricio.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.example.fabricio.myapplication.Util.AppUtil;
import com.example.fabricio.myapplication.model.persistence.SQliteUserRepository;

import java.io.Serializable;

/**
 * Created by Fabricio on 30/07/2015.
 */
public class User implements Serializable, Parcelable {

    public User() {
        super();
    }

    public User(Parcel in) {
        super();
        readToParcel(in);
    }

    private Integer id;
    private String userName;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean authentication() {
        if (!(AppUtil.stringIsNullOrEmpty(password) && AppUtil.stringIsNullOrEmpty(password))) {
            return SQliteUserRepository.getInstance().search(this).size() > 0;
        }
        return false;
    }

    public void save() {
        SQliteUserRepository.getInstance().save(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null)
            return false;
        return !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(userName == null ? "" : userName);
        dest.writeString(password == null ? "" : password);
    }

    private void readToParcel(Parcel in) {
        int partialId = in.readInt();
        id = partialId == -1 ? null : partialId;
        userName = in.readString();
        password = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };


}
