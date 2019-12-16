package org.hawkular.alerts.api.model.condition;

import org.hawkular.alerts.api.doc.DocModel;
import org.hawkular.alerts.api.doc.DocModelProperty;
import org.hawkular.alerts.api.model.condition.Condition.Type;
import org.hawkular.alerts.api.model.event.Event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * An evaluation state for event condition.
 *
 * @author Jay Shaughnessy
 * @author Lucas Ponce
 */
@DocModel(description = "An evaluation state for event condition.")
public class EventConditionEval extends ConditionEval {

    private static final long serialVersionUID = 1L;

    @DocModelProperty(description = "Event condition linked with this state.",
            position = 0)
    @JsonInclude(Include.NON_NULL)
    private EventCondition condition;

    @DocModelProperty(description = "Event value used for dataId.",
            position = 1)
    @JsonInclude(Include.NON_NULL)
    private Event value;

    public EventConditionEval() {
        super(Type.EVENT, false, 0, null);
        this.condition = null;
        this.value = null;
    }

    public EventConditionEval(EventCondition condition, Event value) {
        super(Type.EVENT, condition.match(value), value.getCtime(), value.getContext());
        this.condition = condition;
        this.value = value;
    }

    public EventCondition getCondition() {
        return condition;
    }

    public void setCondition(EventCondition condition) {
        this.condition = condition;
    }

    public Event getValue() {
        return value;
    }

    public void setValue(Event value) {
        this.value = value;
    }

    @Override
    public String getTenantId() {
        return condition.getTenantId();
    }

    @Override
    public String getTriggerId() {
        return condition.getTriggerId();
    }

    @Override
    public int getConditionSetSize() {
        return condition.getConditionSetSize();
    }

    @Override
    public int getConditionSetIndex() {
        return condition.getConditionSetIndex();
    }

    @Override
    public void updateDisplayString() {
        String cond = condition.getExpression() != null && condition.getExpression().length() > 0 ? condition.getExpression() : condition.getExpr();
        String s = String.format("Event: %s[%s] matches [%s]", condition.getDataId(), value, cond);
        setDisplayString(s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        EventConditionEval that = (EventConditionEval) o;

        if (condition != null ? !condition.equals(that.condition) : that.condition != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "EventConditionEval{" +
                "condition=" + condition +
                ", value=" + value +
                '}';
    }
}
