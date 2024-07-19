package com.connect.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_posts")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false,nullable=false)
	private int id;
	
	@Column(nullable=true)
	private String content;
	
	@Column(nullable=true)
	private String content_updated;
	
	@Column(nullable=true)
	private String imageUrl;
	
	@Column(nullable=true)
	private String videoUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false, referencedColumnName="id")
	private User creator;
	
	@Column(nullable=false,columnDefinition="tinyint(1) default 0")
	private boolean isArchieved = false;
	
	@Column(nullable=true)
	private LocalDateTime updatedAt;
	
	@Column(nullable=true)
	private LocalDateTime archievedAt;
	
	@Column(nullable=false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Comment> commentsList = new ArrayList<>();

	
	public Post () {  }
	
	public Post(String content, User creator) {
		this.content = content;
		this.creator = creator;
	}
	
	public Post(String content, String imageUrl, User creator) {
		this.content = content;
		this.imageUrl = imageUrl;
		this.creator = creator;
	}

	public Post(String content, String imageUrl, String videoUrl, User creator) {
		this.content = content;
		this.imageUrl = imageUrl;
		this.videoUrl = videoUrl;
		this.creator = creator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_updated() {
		return content_updated;
	}

	public void setContent_updated(String content_updated) {
//		this.content_updated = getContent().concat(content_updated);
		this.content_updated = content_updated;
	}

	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getVideoUrl() {
		return videoUrl;
	}



	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}



	public User getCreator() {
		return creator;
	}



	public void setCreator(User creator) {
		this.creator = creator;
	}



	public boolean isArchieved() {
		return isArchieved;
	}



	public void setArchieved(boolean isArchieved) {
		this.isArchieved = isArchieved;
	}



	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	public LocalDateTime getArchievedAt() {
		return archievedAt;
	}



	public void setArchievedAt(LocalDateTime archievedAt) {
		this.archievedAt = archievedAt;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	
	public List<Comment> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", content_updated=" + content_updated + ", imageUrl="
				+ imageUrl + ", videoUrl=" + videoUrl + ", creator=" + creator + ", isArchieved=" + isArchieved
				+ ", updatedAt=" + updatedAt + ", archievedAt=" + archievedAt + ", createdAt=" + createdAt + "]";
	}
	
	
	

	
}
