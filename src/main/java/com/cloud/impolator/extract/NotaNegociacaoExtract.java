/*
 * package com.cloud.impolator.extract;
 * 
 * import java.io.IOException; import java.util.HashMap;
 * 
 * import org.springframework.web.multipart.MultipartFile;
 * 
 * import com.cloud.impolator.entity.notanegociacao.NotaNegociacao;
 * 
 * 
 * public class NotaNegociacaoExtract {
 * 
 * public static HashMap<String, Integer>
 * getPositionOfNotaNegociacao(MultipartFile file) {
 * 
 * HashMap<String, Integer> map = new HashMap<>();
 * 
 * for (int y = 0; y < 1000; y++) {
 * 
 * try { if (UtilsExtract.getTextFromCoordinate(file, 32, y, 800,
 * 1).contains("Nr. nota")) { map.put("startPosition", y);
 * map.put("endPosition", y+30); break; } } catch (IOException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } }
 * 
 * return map; }
 * 
 * public static HashMap<String, Integer> getPositionOfCorretora(MultipartFile
 * file) {
 * 
 * HashMap<String, Integer> map = new HashMap<>();
 * 
 * for (int y = 0; y < 1000; y++) {
 * 
 * try {
 * 
 * if (map.get("startPosition") == null &&
 * UtilsExtract.getTextFromCoordinate(file, 32, y, 800, 1).contains("Folha")) {
 * map.put("startPosition", y +12); }
 * 
 * if (map.get("startPosition") != null &&
 * UtilsExtract.getTextFromCoordinate(file, 32, y, 80, 1).equals("Cliente")) {
 * map.put("endPosition", y-1); break; }
 * 
 * } catch (IOException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * }
 * 
 * return map; }
 * 
 * public NotaNegociacao getValuesOfNotaNegociacao(MultipartFile file) throws
 * IOException{
 * 
 * HashMap<String, Integer> map = getPositionOfNotaNegociacao(file); Integer
 * startPosition = map.get("startPosition"); Integer endPosition =
 * map.get("endPosition");
 * 
 * NotaNegociacao notaNeg = new NotaNegociacao(); for (int y = startPosition; y
 * < endPosition; y++) {
 * 
 * if (notaNeg.getNumNota() == null && UtilsExtract.getTextFromCoordinate(file,
 * 430, y, 35, 1).equals("Nr. nota")) {
 * notaNeg.setNumNota(UtilsExtract.getTextFromCoordinate(file, 430, y+1, 35,
 * 8)); // Value of column "Nr. nota" }
 * 
 * if (notaNeg.getFolha() == null && UtilsExtract.getTextFromCoordinate(file,
 * 475, y, 40, 1).equals("Folha")) {
 * notaNeg.setFolha(UtilsExtract.getTextFromCoordinate(file, 475, y+1, 40, 8));
 * // Value of column "Folha" }
 * 
 * if (notaNeg.getDataPregao() == null &&
 * UtilsExtract.getTextFromCoordinate(file, 516, y, 40,
 * 1).equals("Data pregão")) {
 * notaNeg.setDataPregao(UtilsExtract.getTextFromCoordinate(file, 516, y+1, 40,
 * 8)); // Value of column "Data pregão" } }
 * 
 * // Dados da Corretora HashMap<String, Integer> mapCor =
 * getPositionOfCorretora(file); Integer startPositionCor =
 * mapCor.get("startPosition");
 * 
 * notaNeg.setDescricao(UtilsExtract.getTextFromCoordinate(file, 32,
 * startPositionCor, 700, 70)); // Value of column "Dados da corretora"
 * 
 * return notaNeg;
 * 
 * }
 * 
 * 
 * 
 * }
 */