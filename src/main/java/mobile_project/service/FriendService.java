package mobile_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobile_project.bean.Friends;
import mobile_project.bean.User;
import mobile_project.dao.FriendsMapper;

@Service
public class FriendService {

	@Autowired
	private FriendsMapper friendsMapper;

	// 添加用户XXX为好友
	public int addFriends(Friends f) {
		int result = friendsMapper.insert(f);
		return result;

	}
	
	public int deleteFriends(Friends f) {
		int result = friendsMapper.deleteByFriend(f);
		return result;
		
	}
}
