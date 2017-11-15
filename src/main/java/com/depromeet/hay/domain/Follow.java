package com.depromeet.hay.domain;

public class Follow {

    private int followingId;
    private int followerId;

    public Follow(int followingId, int followerId) {
        this.followingId = followingId;
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }
}
