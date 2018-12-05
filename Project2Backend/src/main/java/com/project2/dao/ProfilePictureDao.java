package com.project2.dao;

import com.project2.models.ProfilePicture;

public interface ProfilePictureDao {
	ProfilePicture  saveOrUpdateProfilePic(ProfilePicture profilePicture);
	ProfilePicture  getImage(String email);
}
