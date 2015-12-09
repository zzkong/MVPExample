package lico.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zwl on 2015/9/23.
 */
public class ResponseImagesListEntity implements Parcelable {

    public String col;
    public String tag;
    public String tag3;
    public String sort;
    public int totalNum;
    public int startIndex;
    public int returnNumber;
    public List<ImagesListEntity> imgs;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.col);
        dest.writeString(this.tag);
        dest.writeString(this.tag3);
        dest.writeString(this.sort);
        dest.writeInt(this.totalNum);
        dest.writeInt(this.startIndex);
        dest.writeInt(this.returnNumber);
        dest.writeTypedList(imgs);
    }

    public ResponseImagesListEntity() {
    }

    protected ResponseImagesListEntity(Parcel in) {
        this.col = in.readString();
        this.tag = in.readString();
        this.tag3 = in.readString();
        this.sort = in.readString();
        this.totalNum = in.readInt();
        this.startIndex = in.readInt();
        this.returnNumber = in.readInt();
        this.imgs = in.createTypedArrayList(ImagesListEntity.CREATOR);
    }

    public static final Creator<ResponseImagesListEntity> CREATOR = new Creator<ResponseImagesListEntity>() {
        public ResponseImagesListEntity createFromParcel(Parcel source) {
            return new ResponseImagesListEntity(source);
        }

        public ResponseImagesListEntity[] newArray(int size) {
            return new ResponseImagesListEntity[size];
        }
    };
}
