package com.startdt.modules.role.dal.pojo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GrantPermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GrantPermissionExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return !criteria.isEmpty();
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value) {
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2) {
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value);
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value);
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value);
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value);
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value);
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value);
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values);
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values);
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartIsNull() {
            addCriterion("principal_part is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalPartIsNotNull() {
            addCriterion("principal_part is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalPartEqualTo(String value) {
            addCriterion("principal_part =", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartNotEqualTo(String value) {
            addCriterion("principal_part <>", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartGreaterThan(String value) {
            addCriterion("principal_part >", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartGreaterThanOrEqualTo(String value) {
            addCriterion("principal_part >=", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartLessThan(String value) {
            addCriterion("principal_part <", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartLessThanOrEqualTo(String value) {
            addCriterion("principal_part <=", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartLike(String value) {
            addCriterion("principal_part like", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartNotLike(String value) {
            addCriterion("principal_part not like", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartIn(List<String> values) {
            addCriterion("principal_part in", values);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartNotIn(List<String> values) {
            addCriterion("principal_part not in", values);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartBetween(String value1, String value2) {
            addCriterion("principal_part between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartNotBetween(String value1, String value2) {
            addCriterion("principal_part not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeIsNull() {
            addCriterion("principal_part_type is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeIsNotNull() {
            addCriterion("principal_part_type is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeEqualTo(Byte value) {
            addCriterion("principal_part_type =", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeNotEqualTo(Byte value) {
            addCriterion("principal_part_type <>", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeGreaterThan(Byte value) {
            addCriterion("principal_part_type >", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("principal_part_type >=", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeLessThan(Byte value) {
            addCriterion("principal_part_type <", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeLessThanOrEqualTo(Byte value) {
            addCriterion("principal_part_type <=", value);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeIn(List<Byte> values) {
            addCriterion("principal_part_type in", values);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeNotIn(List<Byte> values) {
            addCriterion("principal_part_type not in", values);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeBetween(Byte value1, Byte value2) {
            addCriterion("principal_part_type between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andPrincipalPartTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("principal_part_type not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andResourcesIsNull() {
            addCriterion("resources is null");
            return (Criteria) this;
        }

        public Criteria andResourcesIsNotNull() {
            addCriterion("resources is not null");
            return (Criteria) this;
        }

        public Criteria andResourcesEqualTo(String value) {
            addCriterion("resources =", value);
            return (Criteria) this;
        }

        public Criteria andResourcesNotEqualTo(String value) {
            addCriterion("resources <>", value);
            return (Criteria) this;
        }

        public Criteria andResourcesGreaterThan(String value) {
            addCriterion("resources >", value);
            return (Criteria) this;
        }

        public Criteria andResourcesGreaterThanOrEqualTo(String value) {
            addCriterion("resources >=", value);
            return (Criteria) this;
        }

        public Criteria andResourcesLessThan(String value) {
            addCriterion("resources <", value);
            return (Criteria) this;
        }

        public Criteria andResourcesLessThanOrEqualTo(String value) {
            addCriterion("resources <=", value);
            return (Criteria) this;
        }

        public Criteria andResourcesLike(String value) {
            addCriterion("resources like", value);
            return (Criteria) this;
        }

        public Criteria andResourcesNotLike(String value) {
            addCriterion("resources not like", value);
            return (Criteria) this;
        }

        public Criteria andResourcesIn(List<String> values) {
            addCriterion("resources in", values);
            return (Criteria) this;
        }

        public Criteria andResourcesNotIn(List<String> values) {
            addCriterion("resources not in", values);
            return (Criteria) this;
        }

        public Criteria andResourcesBetween(String value1, String value2) {
            addCriterion("resources between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andResourcesNotBetween(String value1, String value2) {
            addCriterion("resources not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeIsNull() {
            addCriterion("resources_type is null");
            return (Criteria) this;
        }

        public Criteria andResourcesTypeIsNotNull() {
            addCriterion("resources_type is not null");
            return (Criteria) this;
        }

        public Criteria andResourcesTypeEqualTo(Byte value) {
            addCriterion("resources_type =", value);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeNotEqualTo(Byte value) {
            addCriterion("resources_type <>", value);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeGreaterThan(Byte value) {
            addCriterion("resources_type >", value);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("resources_type >=", value);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeLessThan(Byte value) {
            addCriterion("resources_type <", value);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeLessThanOrEqualTo(Byte value) {
            addCriterion("resources_type <=", value);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeIn(List<Byte> values) {
            addCriterion("resources_type in", values);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeNotIn(List<Byte> values) {
            addCriterion("resources_type not in", values);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeBetween(Byte value1, Byte value2) {
            addCriterion("resources_type between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andResourcesTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("resources_type not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeIsNull() {
            addCriterion("space_code is null");
            return (Criteria) this;
        }

        public Criteria andSpaceCodeIsNotNull() {
            addCriterion("space_code is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceCodeEqualTo(String value) {
            addCriterion("space_code =", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeNotEqualTo(String value) {
            addCriterion("space_code <>", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeGreaterThan(String value) {
            addCriterion("space_code >", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("space_code >=", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeLessThan(String value) {
            addCriterion("space_code <", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeLessThanOrEqualTo(String value) {
            addCriterion("space_code <=", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeLike(String value) {
            addCriterion("space_code like", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeNotLike(String value) {
            addCriterion("space_code not like", value);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeIn(List<String> values) {
            addCriterion("space_code in", values);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeNotIn(List<String> values) {
            addCriterion("space_code not in", values);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeBetween(String value1, String value2) {
            addCriterion("space_code between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andSpaceCodeNotBetween(String value1, String value2) {
            addCriterion("space_code not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value);
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value);
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value);
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value);
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value);
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value);
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value);
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value);
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values);
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values);
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value);
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value);
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value);
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value);
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value);
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value);
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values);
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values);
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value);
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value);
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value);
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value);
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value);
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value);
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values);
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values);
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2);
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2);
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}