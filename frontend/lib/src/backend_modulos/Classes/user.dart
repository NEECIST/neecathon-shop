class User{
  int id;
  String name, password, email, linkedIn, gitHub, teamName;
  User.empty();
  User(this.id, this.name, this.password, this.email, this.linkedIn, this.gitHub, this.teamName);
  String toString() => '$id: $name $email';
  Map toJson() => {'id': id, 'username': name, 'password': password, 'email': email, 'linkedIn': linkedIn, 'gitHub': gitHub, 'teamName': teamName};
  Map toJsonLogin() => {'email': email, 'password': password};
}
