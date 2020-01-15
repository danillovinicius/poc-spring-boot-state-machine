package com.dvlima.archetype.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.dvlima.archetype.business.enummerator.StatusEnum;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "TB_VAGA")
public class Vaga implements Serializable {

  private static final long serialVersionUID = 8321745850194464616L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Size(min = 3, max = 20)
  @Column(name = "NOME")
  private String nome;

  @Column(name = "STATUS")
  private StatusEnum status;

  @Column(name = "ATIVO")
  private boolean active;

}
