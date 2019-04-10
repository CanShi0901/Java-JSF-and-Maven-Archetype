DROP TABLE IF exists public.student;

CREATE TABLE public.student (
  id              int not null IDENTITY,
  first_name      varchar(100),
  last_name       varchar(100),
  primary key (id)
);
INSERT INTO public.student (first_name, last_name) VALUES('Mickey', 'Mouse');
