package hwk2.cis350.upenn.edu.wolfofwharton;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


//The adapters View Holder
public class View_Holder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView name;
    TextView numShares;
    TextView priceChange;

    View_Holder(View itemView) {
        super(itemView);
        cv = itemView.findViewById(R.id.cardView);
        name = itemView.findViewById(R.id.name);
        numShares = itemView.findViewById(R.id.numShares);
        priceChange = itemView.findViewById(R.id.priceChange);
    }
}
