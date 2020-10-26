create table osworks.comentario(
	id bigint not null auto_increment,
    ordem_servico_id bigint not null,
    descricao text not null,
    data_envio datetime not null,

    primary key (id)
);

alter table osworks.comentario add constraint fk_comentario_ordem_servico foreign key (ordem_servico_id) references osworks.ordem_servico (id);