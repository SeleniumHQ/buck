/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class ObtainAllAvailableCapacityResponse implements org.apache.thrift.TBase<ObtainAllAvailableCapacityResponse, ObtainAllAvailableCapacityResponse._Fields>, java.io.Serializable, Cloneable, Comparable<ObtainAllAvailableCapacityResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ObtainAllAvailableCapacityResponse");

  private static final org.apache.thrift.protocol.TField OBTAINED_CAPACITY_FIELD_DESC = new org.apache.thrift.protocol.TField("obtainedCapacity", org.apache.thrift.protocol.TType.I32, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ObtainAllAvailableCapacityResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ObtainAllAvailableCapacityResponseTupleSchemeFactory();

  public int obtainedCapacity; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    OBTAINED_CAPACITY((short)1, "obtainedCapacity");

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
        case 1: // OBTAINED_CAPACITY
          return OBTAINED_CAPACITY;
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
  private static final int __OBTAINEDCAPACITY_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.OBTAINED_CAPACITY};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.OBTAINED_CAPACITY, new org.apache.thrift.meta_data.FieldMetaData("obtainedCapacity", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ObtainAllAvailableCapacityResponse.class, metaDataMap);
  }

  public ObtainAllAvailableCapacityResponse() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ObtainAllAvailableCapacityResponse(ObtainAllAvailableCapacityResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.obtainedCapacity = other.obtainedCapacity;
  }

  public ObtainAllAvailableCapacityResponse deepCopy() {
    return new ObtainAllAvailableCapacityResponse(this);
  }

  @Override
  public void clear() {
    setObtainedCapacityIsSet(false);
    this.obtainedCapacity = 0;
  }

  public int getObtainedCapacity() {
    return this.obtainedCapacity;
  }

  public ObtainAllAvailableCapacityResponse setObtainedCapacity(int obtainedCapacity) {
    this.obtainedCapacity = obtainedCapacity;
    setObtainedCapacityIsSet(true);
    return this;
  }

  public void unsetObtainedCapacity() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __OBTAINEDCAPACITY_ISSET_ID);
  }

  /** Returns true if field obtainedCapacity is set (has been assigned a value) and false otherwise */
  public boolean isSetObtainedCapacity() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __OBTAINEDCAPACITY_ISSET_ID);
  }

  public void setObtainedCapacityIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __OBTAINEDCAPACITY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case OBTAINED_CAPACITY:
      if (value == null) {
        unsetObtainedCapacity();
      } else {
        setObtainedCapacity((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case OBTAINED_CAPACITY:
      return getObtainedCapacity();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case OBTAINED_CAPACITY:
      return isSetObtainedCapacity();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ObtainAllAvailableCapacityResponse)
      return this.equals((ObtainAllAvailableCapacityResponse)that);
    return false;
  }

  public boolean equals(ObtainAllAvailableCapacityResponse that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_obtainedCapacity = true && this.isSetObtainedCapacity();
    boolean that_present_obtainedCapacity = true && that.isSetObtainedCapacity();
    if (this_present_obtainedCapacity || that_present_obtainedCapacity) {
      if (!(this_present_obtainedCapacity && that_present_obtainedCapacity))
        return false;
      if (this.obtainedCapacity != that.obtainedCapacity)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetObtainedCapacity()) ? 131071 : 524287);
    if (isSetObtainedCapacity())
      hashCode = hashCode * 8191 + obtainedCapacity;

    return hashCode;
  }

  @Override
  public int compareTo(ObtainAllAvailableCapacityResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetObtainedCapacity()).compareTo(other.isSetObtainedCapacity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetObtainedCapacity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.obtainedCapacity, other.obtainedCapacity);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ObtainAllAvailableCapacityResponse(");
    boolean first = true;

    if (isSetObtainedCapacity()) {
      sb.append("obtainedCapacity:");
      sb.append(this.obtainedCapacity);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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

  private static class ObtainAllAvailableCapacityResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ObtainAllAvailableCapacityResponseStandardScheme getScheme() {
      return new ObtainAllAvailableCapacityResponseStandardScheme();
    }
  }

  private static class ObtainAllAvailableCapacityResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<ObtainAllAvailableCapacityResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ObtainAllAvailableCapacityResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // OBTAINED_CAPACITY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.obtainedCapacity = iprot.readI32();
              struct.setObtainedCapacityIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ObtainAllAvailableCapacityResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetObtainedCapacity()) {
        oprot.writeFieldBegin(OBTAINED_CAPACITY_FIELD_DESC);
        oprot.writeI32(struct.obtainedCapacity);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ObtainAllAvailableCapacityResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ObtainAllAvailableCapacityResponseTupleScheme getScheme() {
      return new ObtainAllAvailableCapacityResponseTupleScheme();
    }
  }

  private static class ObtainAllAvailableCapacityResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<ObtainAllAvailableCapacityResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ObtainAllAvailableCapacityResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetObtainedCapacity()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetObtainedCapacity()) {
        oprot.writeI32(struct.obtainedCapacity);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ObtainAllAvailableCapacityResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.obtainedCapacity = iprot.readI32();
        struct.setObtainedCapacityIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

