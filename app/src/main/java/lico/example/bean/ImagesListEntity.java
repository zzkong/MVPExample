package lico.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zwl on 2015/9/23.
 */
public class ImagesListEntity implements Parcelable {

    public String id;
    public String desc;
    public List<String> tags;
    public String fromPageTitle;
    public String column;
    public String date;
    public String downloadUrl;
    public String imageUrl;
    public int imageWidth;
    public int imageHeight;
    public String thumbnailUrl;
    public int thumbnailWidth;
    public int thumbnailHeight;
    public String thumbnailLargeUrl;
    public int thumbnailLargeWidth;
    public int thumbnailLargeHeight;
    public String thumbnailLargeTnUrl;
    public int thumbnailLargeTnWidth;
    public int thumbnailLargeTnHeight;
    public String siteName;
    public String siteLogo;
    public String siteUrl;
    public String fromUrl;
    public String objUrl;
    public String shareUrl;
    public String albumId;
    public int isAlbum;
    public String albumName;
    public int albumNum;
    public String title;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.desc);
        dest.writeStringList(this.tags);
        dest.writeString(this.fromPageTitle);
        dest.writeString(this.column);
        dest.writeString(this.date);
        dest.writeString(this.downloadUrl);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.imageWidth);
        dest.writeInt(this.imageHeight);
        dest.writeString(this.thumbnailUrl);
        dest.writeInt(this.thumbnailWidth);
        dest.writeString(this.thumbnailLargeUrl);
        dest.writeInt(this.thumbnailHeight);
        dest.writeInt(this.thumbnailLargeWidth);
        dest.writeInt(this.thumbnailLargeHeight);
        dest.writeString(this.thumbnailLargeTnUrl);
        dest.writeInt(this.thumbnailLargeTnWidth);
        dest.writeInt(this.thumbnailLargeTnHeight);
        dest.writeString(this.siteName);
        dest.writeString(this.siteLogo);
        dest.writeString(this.siteUrl);
        dest.writeString(this.fromUrl);
        dest.writeString(this.objUrl);
        dest.writeString(this.shareUrl);
        dest.writeString(this.albumId);
        dest.writeInt(this.isAlbum);
        dest.writeString(this.albumName);
        dest.writeInt(this.albumNum);
        dest.writeString(this.title);
    }

    public ImagesListEntity() {
    }

    protected ImagesListEntity(Parcel in) {
        this.id = in.readString();
        this.desc = in.readString();
        this.tags = in.createStringArrayList();
        this.fromPageTitle = in.readString();
        this.column = in.readString();
        this.date = in.readString();
        this.downloadUrl = in.readString();
        this.imageUrl = in.readString();
        this.imageWidth = in.readInt();
        this.imageHeight = in.readInt();
        this.thumbnailUrl = in.readString();
        this.thumbnailWidth = in.readInt();
        this.thumbnailLargeUrl = in.readString();
        this.thumbnailHeight = in.readInt();
        this.thumbnailLargeWidth = in.readInt();
        this.thumbnailLargeHeight = in.readInt();
        this.thumbnailLargeTnUrl = in.readString();
        this.thumbnailLargeTnWidth = in.readInt();
        this.thumbnailLargeTnHeight = in.readInt();
        this.siteName = in.readString();
        this.siteLogo = in.readString();
        this.siteUrl = in.readString();
        this.fromUrl = in.readString();
        this.objUrl = in.readString();
        this.shareUrl = in.readString();
        this.albumId = in.readString();
        this.isAlbum = in.readInt();
        this.albumName = in.readString();
        this.albumNum = in.readInt();
        this.title = in.readString();
    }

    public static final Creator<ImagesListEntity> CREATOR = new Creator<ImagesListEntity>() {
        public ImagesListEntity createFromParcel(Parcel source) {
            return new ImagesListEntity(source);
        }

        public ImagesListEntity[] newArray(int size) {
            return new ImagesListEntity[size];
        }
    };
}
