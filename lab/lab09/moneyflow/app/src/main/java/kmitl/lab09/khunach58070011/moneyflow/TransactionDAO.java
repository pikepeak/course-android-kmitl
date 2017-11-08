package kmitl.lab09.khunach58070011.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TransactionDAO {
    @Query("SELECT * FROM TRANSACTION_INFO")
    List<TransactionInfo> getAll();

    @Insert
    void insert(TransactionInfo transactionInfo);

    @Query("DELETE FROM TRANSACTION_INFO WHERE id like :ids")
    int deleteid(int ids);

    @Query("UPDATE TRANSACTION_INFO SET STATUS = :type,NAME = :item ,MONEY = :amount WHERE id =:ids")
    int UpdateColumn(String type, String item, int amount, int ids);
}
