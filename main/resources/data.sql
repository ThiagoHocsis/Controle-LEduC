INSERT INTO projeto (id, nome_projeto) VALUES (1, 'teste' );
INSERT INTO usuario (id, nome_usuario, username, senha,projeto_id) VALUES (1,'thiago', 'admin', 'admin','1');
INSERT INTO usuario (id, nome_usuario, username, senha,projeto_id) VALUES (2,'bruna', 'bruna', 'bruna','1');



insert into usuario_roles(usuario_id,roles) values (1,'ADMIN');
insert into usuario_roles(usuario_id,roles) values (1,'USER');
insert into usuario_roles(usuario_id,roles) values (2,'USER');
insert into usuario_roles(usuario_id,roles) values (2,'ADMIN');




