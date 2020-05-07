package domain.service

import domain.model.User

class UserService {
  def exists(user: User): Boolean = {
    // User からuser名がひとしいものをfindし、いなければtrue さもなければ false
    // これもDBにさわりにいくので大変。。。
    ???
  }
}
