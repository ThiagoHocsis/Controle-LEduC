INSERT INTO usuario (id, nome, username, senha) VALUES (1,'thiago', 'admin', 'admin');
INSERT INTO usuario (id, nome, username, senha) VALUES (2,'bruna', 'bruna', 'bruna');



insert into usuario_roles(usuario_id,roles) values (1,'ADMIN');
insert into usuario_roles(usuario_id,roles) values (1,'USER');
insert into usuario_roles(usuario_id,roles) values (2,'USER');
insert into usuario_roles(usuario_id,roles) values (2,'ADMIN');


