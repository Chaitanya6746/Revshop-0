package model;

public class User {
	private int UserId;
    private String username;
    private String password;
    private String email;
	private String role;
	private String address;
	private long phone_num;

    // Getters and Setters
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
		return email;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public void setRole(String role) {
		// TODO Auto-generated method stub
		this.role = role;
	}
	public String getRole() {
		return role;
	}

	public void setaddress(String address) {
		// TODO Auto-generated method stub
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public long getPhone_num() {
		return phone_num;
	}

	public void setphonenumber(long phone_num) {
		// TODO Auto-generated method stub
		this.phone_num = phone_num;
	}
}
