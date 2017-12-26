
insert into roleRights (rightsDescription, adding, commenting, attachingFiles, stateVeiw, updateOrdter, deleteOrder)
values ('All rights', true, true , true, true, true , true);

insert into roleRights (rightsDescription, adding, commenting, attachingFiles, stateVeiw, updateOrdter, deleteOrder)
values ('client rights', true, true , true, true, true , false);
 
insert into roleInSystem (fk_id_roleRights, typeOfRole)
values ((select pk_id from roleRights where rightsDescription = 'client rights'), 'user');

insert into roleInSystem (fk_id_roleRights, typeOfRole)
values ((select pk_id from roleRights where rightsDescription = 'All rights'), 'ownClient');

insert into category (categoryName) values ('service');
insert into category (categoryName) values ('develop');
insert into category (categoryName) values ('buy');

insert into state (stateName) values ('proccessed');
insert into state (stateName) values ('in_proccess');
insert into state (stateName) values ('accepted');
insert into state (stateName) values ('underReview');
insert into state (stateName) values ('mainTenance');

insert into orders (fk_id_state, fk_id_category, orderName, description, created_date) 
values ((select pk_id from state where stateName = 'in_proccess'),
	(select pk_id from category where categoryName = 'develop'),
	'program', 'order to developing progaram', '2017.12.24 22:35');

insert into orders (fk_id_state, fk_id_category, orderName, description, created_date) 
values ((select pk_id from state where stateName = 'accepted'),
	(select pk_id from category where categoryName = 'service'),
	'TowerBuildNetworkService', 'order to developing network', '2017.11.24 23:35');

insert into users (fk_id_roleInSystem, fk_id_orders, lastName, phoneNumber, userLogin, userPassword, userName, email) 
values((select pk_id from roleInSystem where typeOfRole = 'user'), 1,
'Larat', '123456789', 'Larat_login', 'Larat_Password', 'David', 'david@mail.com');

insert into users (fk_id_roleInSystem, fk_id_orders, lastName, phoneNumber, userLogin, userPassword, userName, email) 
values((select pk_id from roleInSystem where typeOfRole = 'ownClient'), 2,
'Page', '987654321', 'Page_login', 'Page_Password', 'Tray', 'tray@mail.com');


insert into attachedFiles (fk_id_orders, filesPath) values (1, 'C:\\file1.jpg');
insert into attachedFiles (fk_id_orders, filesPath) values (1, 'C:\\file2.jpg');


insert into comments (fk_id_orders, summary, description)
values (1, 'coment to order 1', 'description to coment 1');
insert into comments (fk_id_orders, summary, description) 
values (2, 'coment to order 2', 'description to coment 2');

