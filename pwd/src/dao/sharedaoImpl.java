package dao;

import java.util.List;

public interface sharedaoImpl {
	public List<share> Select_share(String key);
	public List<share> Select_user_share(String openid);
	public void Addshare(share s);
	public void Deleteshare(String key);
}
