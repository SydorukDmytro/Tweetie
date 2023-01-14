delete from user_role;

delete from usr;

insert into usr(id, active, username, password)
values
(1, true, 'sydorukd', '$2a$08$J/1mBkfPUiVgzeoTf7x2Ie.exHY3Tr/cZhaLq6e.mAa.O04DSVOYS'),
(2, true, 'Sydoruk Dmytro', '$2a$08$POqgvDmozPh40/2y.pPV9OYtKizMYOwGSRzsNHnLqKDuJ5ENyfWFK');

insert into user_role (user_id, roles) VALUES
(1, 'USER'),
(2, 'USER'), (2, 'ADMIN');