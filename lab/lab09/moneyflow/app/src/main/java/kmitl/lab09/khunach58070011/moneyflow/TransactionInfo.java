package kmitl.lab09.khunach58070011.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TRANSACTION_INFO")
class TransactionInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "STATUS")
    private String status;

    @ColumnInfo(name = "NAME")
    private String name;

    @ColumnInfo(name = "MONEY")
    private int money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
