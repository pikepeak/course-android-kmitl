package kmitl.lab07.khunach58070011.mylazyinstagram.api;

import java.util.List;

import kmitl.lab07.khunach58070011.mylazyinstagram.model.listposts;

public class UserProfile {
    private String user;
    private String urlProfile;
    private List<listposts> posts;
    private String bio;
    private String follower;
    private String following;
    private String post;

    public List<listposts> getPosts() {
        return posts;
    }

    public void setPosts(List<listposts> posts) {
        this.posts = posts;
    }
    public UserProfile() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }
}
