/*
 * package com.cloud.impolator.model;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.cloud.impolator.api.core.service.CrudServiceImpl; import
 * com.cloud.impolator.entity.notanegociacao.NotaNegociacao; import
 * com.cloud.impolator.repository.NotaNegociacaoRepository;
 * 
 * 
 * 
 * @Service public class NotaNegociacaoService extends
 * CrudServiceImpl<NotaNegociacao, Long> {
 * 
 * public NotaNegociacaoService() { super(NotaNegociacaoService.class); }
 * 
 * @Autowired private NotaNegociacaoRepository notaNegociacaoRepository;
 * 
 * @Transactional public NotaNegociacao salvar(NotaNegociacao notaNegociacao) {
 * return notaNegociacaoRepository.save(notaNegociacao); }
 * 
 * @Transactional public NotaNegociacao salvarComFile(MultipartFile file) {
 * 
 * NotaNegociacao notaNegociacao = new NotaNegociacao();
 * 
 * return notaNegociacaoRepository.save(notaNegociacao); }
 * 
 * }
 */