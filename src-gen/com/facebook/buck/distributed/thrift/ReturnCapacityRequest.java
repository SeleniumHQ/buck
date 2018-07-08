/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class ReturnCapacityRequest implements org.apache.thrift.TBase<ReturnCapacityRequest, ReturnCapacityRequest._Fields>, java.io.Serializable, Cloneable, Comparable<ReturnCapacityRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReturnCapacityRequest");

  private static final org.apache.thrift.protocol.TField BUILD_SLAVE_RUN_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("buildSlaveRunId", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField CAPACITY_FIELD_DESC = new org.apache.thrift.protocol.TField("capacity", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ReturnCapacityRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ReturnCapacityRequestTupleSchemeFactory();

  public com.facebook.buck.distributed.thrift.BuildSlaveRunId buildSlaveRunId; // optional
  public int capacity; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BUILD_SLAVE_RUN_ID((short)1, "buildSlaveRunId"),
    CAPACITY((short)2, "capacity");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // BUILD_SLAVE_RUN_ID
          return BUILD_SLAVE_RUN_ID;
        case 2: // CAPACITY
          return CAPACITY;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CAPACITY_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.BUILD_SLAVE_RUN_ID,_Fields.CAPACITY};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BUILD_SLAVE_RUN_ID, new org.apache.thrift.meta_data.FieldMetaData("buildSlaveRunId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.facebook.buck.distributed.thrift.BuildSlaveRunId.class)));
    tmpMap.put(_Fields.CAPACITY, new org.apache.thrift.meta_data.FieldMetaData("capacity", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReturnCapacityRequest.class, metaDataMap);
  }

  public ReturnCapacityRequest() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReturnCapacityRequest(ReturnCapacityRequest other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetBuildSlaveRunId()) {
      this.buildSlaveRunId = new com.facebook.buck.distributed.thrift.BuildSlaveRunId(other.buildSlaveRunId);
    }
    this.capacity = other.capacity;
  }

  public ReturnCapacityRequest deepCopy() {
    return new ReturnCapacityRequest(this);
  }

  @Override
  public void clear() {
    this.buildSlaveRunId = null;
    setCapacityIsSet(false);
    this.capacity = 0;
  }

  public com.facebook.buck.distributed.thrift.BuildSlaveRunId getBuildSlaveRunId() {
    return this.buildSlaveRunId;
  }

  public ReturnCapacityRequest setBuildSlaveRunId(com.facebook.buck.distributed.thrift.BuildSlaveRunId buildSlaveRunId) {
    this.buildSlaveRunId = buildSlaveRunId;
    return this;
  }

  public void unsetBuildSlaveRunId() {
    this.buildSlaveRunId = null;
  }

  /** Returns true if field buildSlaveRunId is set (has been assigned a value) and false otherwise */
  public boolean isSetBuildSlaveRunId() {
    return this.buildSlaveRunId != null;
  }

  public void setBuildSlaveRunIdIsSet(boolean value) {
    if (!value) {
      this.buildSlaveRunId = null;
    }
  }

  public int getCapacity() {
    return this.capacity;
  }

  public ReturnCapacityRequest setCapacity(int capacity) {
    this.capacity = capacity;
    setCapacityIsSet(true);
    return this;
  }

  public void unsetCapacity() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CAPACITY_ISSET_ID);
  }

  /** Returns true if field capacity is set (has been assigned a value) and false otherwise */
  public boolean isSetCapacity() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CAPACITY_ISSET_ID);
  }

  public void setCapacityIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CAPACITY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case BUILD_SLAVE_RUN_ID:
      if (value == null) {
        unsetBuildSlaveRunId();
      } else {
        setBuildSlaveRunId((com.facebook.buck.distributed.thrift.BuildSlaveRunId)value);
      }
      break;

    case CAPACITY:
      if (value == null) {
        unsetCapacity();
      } else {
        setCapacity((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case BUILD_SLAVE_RUN_ID:
      return getBuildSlaveRunId();

    case CAPACITY:
      return getCapacity();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case BUILD_SLAVE_RUN_ID:
      return isSetBuildSlaveRunId();
    case CAPACITY:
      return isSetCapacity();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ReturnCapacityRequest)
      return this.equals((ReturnCapacityRequest)that);
    return false;
  }

  public boolean equals(ReturnCapacityRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_buildSlaveRunId = true && this.isSetBuildSlaveRunId();
    boolean that_present_buildSlaveRunId = true && that.isSetBuildSlaveRunId();
    if (this_present_buildSlaveRunId || that_present_buildSlaveRunId) {
      if (!(this_present_buildSlaveRunId && that_present_buildSlaveRunId))
        return false;
      if (!this.buildSlaveRunId.equals(that.buildSlaveRunId))
        return false;
    }

    boolean this_present_capacity = true && this.isSetCapacity();
    boolean that_present_capacity = true && that.isSetCapacity();
    if (this_present_capacity || that_present_capacity) {
      if (!(this_present_capacity && that_present_capacity))
        return false;
      if (this.capacity != that.capacity)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetBuildSlaveRunId()) ? 131071 : 524287);
    if (isSetBuildSlaveRunId())
      hashCode = hashCode * 8191 + buildSlaveRunId.hashCode();

    hashCode = hashCode * 8191 + ((isSetCapacity()) ? 131071 : 524287);
    if (isSetCapacity())
      hashCode = hashCode * 8191 + capacity;

    return hashCode;
  }

  @Override
  public int compareTo(ReturnCapacityRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetBuildSlaveRunId()).compareTo(other.isSetBuildSlaveRunId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBuildSlaveRunId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.buildSlaveRunId, other.buildSlaveRunId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCapacity()).compareTo(other.isSetCapacity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCapacity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.capacity, other.capacity);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ReturnCapacityRequest(");
    boolean first = true;

    if (isSetBuildSlaveRunId()) {
      sb.append("buildSlaveRunId:");
      if (this.buildSlaveRunId == null) {
        sb.append("null");
      } else {
        sb.append(this.buildSlaveRunId);
      }
      first = false;
    }
    if (isSetCapacity()) {
      if (!first) sb.append(", ");
      sb.append("capacity:");
      sb.append(this.capacity);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (buildSlaveRunId != null) {
      buildSlaveRunId.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ReturnCapacityRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ReturnCapacityRequestStandardScheme getScheme() {
      return new ReturnCapacityRequestStandardScheme();
    }
  }

  private static class ReturnCapacityRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<ReturnCapacityRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReturnCapacityRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // BUILD_SLAVE_RUN_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.buildSlaveRunId = new com.facebook.buck.distributed.thrift.BuildSlaveRunId();
              struct.buildSlaveRunId.read(iprot);
              struct.setBuildSlaveRunIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CAPACITY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.capacity = iprot.readI32();
              struct.setCapacityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReturnCapacityRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.buildSlaveRunId != null) {
        if (struct.isSetBuildSlaveRunId()) {
          oprot.writeFieldBegin(BUILD_SLAVE_RUN_ID_FIELD_DESC);
          struct.buildSlaveRunId.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCapacity()) {
        oprot.writeFieldBegin(CAPACITY_FIELD_DESC);
        oprot.writeI32(struct.capacity);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ReturnCapacityRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ReturnCapacityRequestTupleScheme getScheme() {
      return new ReturnCapacityRequestTupleScheme();
    }
  }

  private static class ReturnCapacityRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<ReturnCapacityRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReturnCapacityRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetBuildSlaveRunId()) {
        optionals.set(0);
      }
      if (struct.isSetCapacity()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetBuildSlaveRunId()) {
        struct.buildSlaveRunId.write(oprot);
      }
      if (struct.isSetCapacity()) {
        oprot.writeI32(struct.capacity);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReturnCapacityRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.buildSlaveRunId = new com.facebook.buck.distributed.thrift.BuildSlaveRunId();
        struct.buildSlaveRunId.read(iprot);
        struct.setBuildSlaveRunIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.capacity = iprot.readI32();
        struct.setCapacityIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

