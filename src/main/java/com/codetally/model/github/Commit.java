
package com.codetally.model.github;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Commit {

    @SerializedName("added")
    private List<String> mAdded;
    @SerializedName("author")
    private Author mAuthor;
    @SerializedName("committer")
    private Committer mCommitter;
    @SerializedName("distinct")
    private Boolean mDistinct;
    @SerializedName("id")
    private String mId;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("modified")
    private List<String> mModified;
    @SerializedName("removed")
    private List<String> mRemoved;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("tree_id")
    private String mTreeId;
    @SerializedName("url")
    private String mUrl;

    public List<String> getAdded() {
        return mAdded;
    }

    public void setAdded(List<String> added) {
        mAdded = added;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    public Committer getCommitter() {
        return mCommitter;
    }

    public void setCommitter(Committer committer) {
        mCommitter = committer;
    }

    public Boolean getDistinct() {
        return mDistinct;
    }

    public void setDistinct(Boolean distinct) {
        mDistinct = distinct;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public List<String> getModified() {
        return mModified;
    }

    public void setModified(List<String> modified) {
        mModified = modified;
    }

    public List<String> getRemoved() {
        return mRemoved;
    }

    public void setRemoved(List<String> removed) {
        mRemoved = removed;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public String getTreeId() {
        return mTreeId;
    }

    public void setTreeId(String treeId) {
        mTreeId = treeId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}