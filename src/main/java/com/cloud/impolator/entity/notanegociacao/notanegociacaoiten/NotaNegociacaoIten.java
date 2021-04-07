/*
 * package com.cloud.impolator.entity.notanegociacao.notanegociacaoiten;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.FetchType; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToOne; import javax.persistence.Table; import
 * javax.validation.constraints.NotNull;
 * 
 * import com.cloud.impolator.entity.notanegociacao.NotaNegociacao; import
 * com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * import lombok.Getter; import lombok.Setter;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @Entity
 * 
 * @Table(name = "nota_negociacao_iten") public class NotaNegociacaoIten {
 * 
 * @NotNull
 * 
 * @Column(name = "q") private String q;
 * 
 * @NotNull
 * 
 * @Column(name = "negociacao") private String negociacao;
 * 
 * private String cv;
 * 
 * private String tipoMercadoria;
 * 
 * private String prazo;
 * 
 * private String especificacaoTitulo;
 * 
 * private String obs;
 * 
 * private String quantidade;
 * 
 * private String precoAjuste;
 * 
 * private String valorOperacao;
 * 
 * private String dc;
 * 
 * @JsonIgnore
 * 
 * @ManyToOne(fetch = FetchType.LAZY)
 * 
 * @JoinColumn(name = "id_fk") private NotaNegociacao notaNegociacao;
 * 
 * }
 */