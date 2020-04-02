package Domain.types;
import Domain.values.Value;

public interface Type {
    String getType();
    Value defaultValue();
}
