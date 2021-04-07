/*
 * package com.cloud.impolator.entity.notanegociacao.notanegociacaoiten;
 * 
 * import java.util.Date;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.cloud.impolator.api.core.repository.HistoricoInclusao; //import
 * com.cloud.impolator.model.NotaNegociacaoService;
 * 
 * 
 * @Service public class NotaNegociacaoItenService implements HistoricoInclusao
 * {
 * 
 * @Autowired private NotaNegociacaoService notaNegociacaoService;
 * 
 * @Autowired private NotaNegociacaoItenRepository notaNegociacaoItenRepository;
 * 
 * public NotaNegociacaoIten incluir(Long idChamado, NotaNegociacaoIten iten) {
 * 
 * iten.setNotaNegociacao(notaNegociacaoService.buscarPorID(idChamado));
 * iten.setNotaNegociacao(notaNegociacaoService.buscarPorID(idChamado));
 * 
 * return notaNegociacaoItenRepository.save(iten); }
 * 
 * public ChamadoComentario incluir(Long idChamado, ChamadoComentario
 * comentario) {
 * 
 * comentario.setChamado(chamadoService.buscarPorID(idChamado));
 * comentario.setUsuarioInclusao(usuarioLogadoService.getUsuarioLogado());
 * comentario.setInclusao(new Date());
 * 
 * return repository.save(comentario); }
 * 
 * 
 * }
 */