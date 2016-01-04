package lico.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zzk on 15/12/30.
 * 泡在网上的日子 数据实体类 http://www.jcodecraeer.com/
 */
public class JcodeInfo implements Parcelable{

    public String detailUrl;
    public String imageUrl;
    public String title;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.detailUrl);
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
    }

    public JcodeInfo() {
    }

    protected JcodeInfo(Parcel in) {
        this.detailUrl = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
    }

    public static final Creator<JcodeInfo> CREATOR = new Creator<JcodeInfo>() {
        public JcodeInfo createFromParcel(Parcel source) {
            return new JcodeInfo(source);
        }

        public JcodeInfo[] newArray(int size) {
            return new JcodeInfo[size];
        }
    };
}
