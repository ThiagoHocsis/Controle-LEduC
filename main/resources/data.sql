
----------INSERINDO PROJETOS----------------
INSERT INTO projeto (id, nome_projeto) VALUES (1, 'PROJETO UM' );
INSERT INTO projeto (id, nome_projeto) VALUES (2, 'PROJETO DOIS' );
INSERT INTO projeto (id, nome_projeto) VALUES (3, 'PROJETO TRES' );



---------INSERINDO USUARIOS-----------------
INSERT INTO usuario (id, nome_usuario, username, senha,projeto_id) VALUES (1,'thiago', 'admin', 'admin','1');
INSERT INTO usuario (id, nome_usuario, username, senha,projeto_id) VALUES (2,'bruna', 'bruna', 'bruna','2');
INSERT INTO usuario (id, nome_usuario, username, senha,projeto_id) VALUES (3,'kobbynho', 'kobby', 'kobby','3');

---------INSERINDO PERMISSÕES DOS USUARIOS ---------------
insert into usuario_roles(usuario_id,roles) values (1,'ADMIN');
insert into usuario_roles(usuario_id,roles) values (1,'USER');
insert into usuario_roles(usuario_id,roles) values (2,'USER');
insert into usuario_roles(usuario_id,roles) values (2,'ADMIN');
insert into usuario_roles(usuario_id,roles) values (3,'USER');


--------INSERINDO TAREFAS ---------------------------------
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (1, 'TAREFA 1', 1);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (2, 'TAREFA 2', 1);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (3, 'TAREFA 3', 1);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (4, 'TAREFA 4', 1);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (5, 'TAREFA 5', 2);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (6, 'TAREFA 6', 2);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (7, 'TAREFA 7', 2);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (8, 'TAREFA 8', 2);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (9, 'TAREFA 9', 3);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (10, 'TAREFA 10', 3);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (11, 'TAREFA 11', 3);
INSERT INTO tarefa(id, nome_tarefa, projeto_id) VALUES (12, 'TAREFA 12', 3);

------INSERINDO USUARIOS EM TAREFAS------------------------

INSERT INTO usuario_tarefa VALUES (1,1);
INSERT INTO usuario_tarefa VALUES (2,1);
INSERT INTO usuario_tarefa VALUES (3,1);
INSERT INTO usuario_tarefa VALUES (4,1);
INSERT INTO usuario_tarefa VALUES (5,2);
INSERT INTO usuario_tarefa VALUES (6,2);
INSERT INTO usuario_tarefa VALUES (7,2);
INSERT INTO usuario_tarefa VALUES (8,2);
INSERT INTO usuario_tarefa VALUES (9,3);
INSERT INTO usuario_tarefa VALUES (10,3);
INSERT INTO usuario_tarefa VALUES (11,3);
INSERT INTO usuario_tarefa VALUES (12,3);






