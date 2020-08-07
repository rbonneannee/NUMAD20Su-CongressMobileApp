package com.cs5520.numad20su_congressmobile.controllers;

import java.util.Set;

/**
 * This interface is to respond to follow/unfollow actions on different items in the application.
 * <p>
 * This interface should be implemented by the activity responsible allows for making updates to the
 * user objects in the remote database. It makes it so that the view classes do not have to make
 * calls to the database API.
 */
public interface FollowInterface {

  enum TYPE {Bill, Committee, Member, Topic}

  /**
   * Follow a given item by adding its uid to the appropriate collection of followed items. A call
   * to this method should also update any persistent storage, i.e. databases.
   *
   * @param type Type of item to follow
   * @param id   Unique identifier of item
   */
  void follow(TYPE type, String id);

  /**
   * Unfollow a given item by removing its uid from the appropriate collection of followed items. A
   * call to this method should also update any persistent storage, i.e. databases.
   *
   * @param type Type of item to unfollow
   * @param id   Unique identifier of item
   */
  void unfollow(TYPE type, String id);

  /**
   * Return an iterable of the uids of the items followed for the given type.
   *
   * @param type specified type
   * @return items followed
   */
  Set<String> following(TYPE type);
}
