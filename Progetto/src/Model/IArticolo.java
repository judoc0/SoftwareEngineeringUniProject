package Model;

import java.util.List;

public interface IArticolo {

     void setPrezzo(Float prezzo); 

     String getNome(); 

     void setNome(String nome); 

     int getId(); 

     void setId(int id); 

     int getIdCategoria(); 

     void setIdCategoria(int idCategoria); 

     String getDescrizione(); 

     void setDescrizione(String descrizione); 

     Float getPrezzo(); 

     CategoriaArticolo getCategoria(); 

     void setCategoria(CategoriaArticolo categoria); 

     byte[] getFoto(); 

     void setFoto(byte[] foto); 

     String getPercorsoFoto(); 

     void setPercorsoFoto(String percorsoFoto); 

     List<FeedBack> getFeedbacks(); 

     void setFeedbacks(List<FeedBack> feedbacks); 
}
