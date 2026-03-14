import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
class DeliveryHistory {
    private List<DeliverySummary> history = new ArrayList<>();

    public List<DeliverySummary> getHistory() {
        return history;
    }

    public void add(DeliverySummary obj) {
        history.add(obj);
    }

    public List<String> summarize() {
        return history
            .stream()
            .map(orderSummary -> orderSummary.toString())
            .collect(Collectors.toList());
    }
}