ndspackage com.connect.domain;

import java.time.LocalDateTime;

import com.connect.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tbl_users")
public class User extends Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false,nullable=false)
	private int id;
	
	@Column(nullable=false, length=50)
	@Size(min=3,max=30,message="Firstname must have atleast 3-30 characters.")
	@NotNull(message="Firstname is required.")
	private String first_name;
	
	@Column(nullable=false, length=50)
	@Size(min=3,max=30,message="Lastname must have atleast 3-30 characters.")
	@NotNull(message="Lastname is required.")
	private String last_name;
	
	@Column(nullable=false, length=100, unique=true)
	@Size(min=3,max=30,message="Username must have atleast 3-100 characters.")
	@NotNull(message="Username is required.")
	private String username;
	
	@Column(nullable=false, length=50)
	@Size(min=1,max=30,message="Firstname must have atleast 3-30 characters.")
	@NotNull(message="Firstname is required.")
	@Email(message="Use a valid email.")
	private String email;
	
	@Column(nullable=false, length=200)
	@Size(min=3,max=30,message="Password must have atleast 6 characters.")
	@NotNull(message="Password is required.")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=true)
	private Gender gender;
	
	@Column(nullable=true)
	private String imageUrl;
	
	@Column(nullable=false, columnDefinition="tinyint(1) default 0")
	private boolean isActive = false;
	
	@Column(nullable=false,columnDefinition="tinyint(1) default 0")
	private boolean isBlocked = false;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", referencedColumnName="id", nullable=false)
	private Role role;
	
	@Column(nullable=true)
	private int activation_code;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
	@Column(nullable=true)
	private int password_reset_code;
	
	@Column(nullable=true)
	private LocalDateTime activated_at;
	
	@Column(nullable=true)
	private LocalDateTime blocked_at;
	
	@Column(nullable=true)
	private LocalDateTime passwordResetAt;
	
	@Column(nullable=true)
	private LocalDateTime updatedAt;
	
	@Column(nullable=false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	public User() {  }
	
	public User(
			@Size(min = 3, max = 30, message = "Firstname must have atleast 3-30 characters.") @NotNull(message = "Firstname is required.") String first_name,
			@Size(min = 3, max = 30, message = "Lastname must have atleast 3-30 characters.") @NotNull(message = "Lastname is required.") String last_name,
			@Size(min = 3, max = 30, message = "Username must have atleast 3-100 characters.") @NotNull(message = "Username is required.") String username,
			@Size(min = 1, max = 30, message = "Firstname must have atleast 3-30 characters.") @NotNull(message = "Firstname is required.") @Email(message = "Use a valid email.") String email,
			@Size(min = 3, max = 30, message = "Password must have atleast 6 characters.") @NotNull(message = "Password is required.") String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.email = email;
		this.password = password;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public int getActivation_code() {
		return activation_code;
	}

	public void setActivation_code(int activation_code) {
		this.activation_code = activation_code;
	}

	public int getPassword_reset_code() {
		return password_reset_code;
	}

	public void setPassword_reset_code(int password_reset_code) {
		this.password_reset_code = password_reset_code;
	}

	public LocalDateTime getActivated_at() {
		return activated_at;
	}

	public void setActivated_at(LocalDateTime activated_at) {
		this.activated_at = activated_at;
	}

	public LocalDateTime getBlocked_at() {
		return blocked_at;
	}

	public void setBlocked_at(LocalDateTime blocked_at) {
		this.blocked_at = blocked_at;
	}

	public LocalDateTime getPasswordResetAt() {
		return passwordResetAt;
	}

	public void setPasswordResetAt(LocalDateTime passwordResetAt) {
		this.passwordResetAt = passwordResetAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", gender=" + gender + ", imageUrl=" + imageUrl
				+ ", isActive=" + isActive + ", isBlocked=" + isBlocked + ", role=" + role + ", activation_code="
				+ activation_code + ", password_reset_code=" + password_reset_code + ", activated_at=" + activated_at
				+ ", blocked_at=" + blocked_at + ", passwordResetAt=" + passwordResetAt + ", updatedAt=" + updatedAt
				+ ", createdAt=" + createdAt + "]";
	}

	
	
}

