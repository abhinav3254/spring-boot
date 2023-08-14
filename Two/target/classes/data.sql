INSERT INTO users (username,password,enabled)
values ('user1','pass',true);


INSERT INTO users (username,password,enabled)
values ('admin1','pass',true);



INSERT INTO authorities(username,authority)
values ('user1','ROLE_USER');

INSERT INTO authorities(username,authority)
values ('admin1','ROLE_ADMIN');

