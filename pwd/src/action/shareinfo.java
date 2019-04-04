package action;

public class shareinfo {
			private String key; 
			private String username;
			private String password;
			private String time;
			public shareinfo(){}
			
			public shareinfo(String key, String username, String password,
					String time) {
				this.key = key;
				this.username = username;
				this.password = password;
				this.time = time;
			}

			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
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
			public String getTime() {
				return time;
			}
			public void setTime(String time) {
				this.time = time;
			}
}
