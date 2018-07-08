/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class Announcement implements org.apache.thrift.TBase<Announcement, Announcement._Fields>, java.io.Serializable, Cloneable, Comparable<Announcement> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Announcement");

  private static final org.apache.thrift.protocol.TField ERROR_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("errorMessage", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SOLUTION_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("solutionMessage", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new AnnouncementStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new AnnouncementTupleSchemeFactory();

  public java.lang.String errorMessage; // optional
  public java.lang.String solutionMessage; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR_MESSAGE((short)1, "errorMessage"),
    SOLUTION_MESSAGE((short)2, "solutionMessage");

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
        case 1: // ERROR_MESSAGE
          return ERROR_MESSAGE;
        case 2: // SOLUTION_MESSAGE
          return SOLUTION_MESSAGE;
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
  private static final _Fields optionals[] = {_Fields.ERROR_MESSAGE,_Fields.SOLUTION_MESSAGE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("errorMessage", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SOLUTION_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("solutionMessage", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Announcement.class, metaDataMap);
  }

  public Announcement() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Announcement(Announcement other) {
    if (other.isSetErrorMessage()) {
      this.errorMessage = other.errorMessage;
    }
    if (other.isSetSolutionMessage()) {
      this.solutionMessage = other.solutionMessage;
    }
  }

  public Announcement deepCopy() {
    return new Announcement(this);
  }

  @Override
  public void clear() {
    this.errorMessage = null;
    this.solutionMessage = null;
  }

  public java.lang.String getErrorMessage() {
    return this.errorMessage;
  }

  public Announcement setErrorMessage(java.lang.String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public void unsetErrorMessage() {
    this.errorMessage = null;
  }

  /** Returns true if field errorMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetErrorMessage() {
    return this.errorMessage != null;
  }

  public void setErrorMessageIsSet(boolean value) {
    if (!value) {
      this.errorMessage = null;
    }
  }

  public java.lang.String getSolutionMessage() {
    return this.solutionMessage;
  }

  public Announcement setSolutionMessage(java.lang.String solutionMessage) {
    this.solutionMessage = solutionMessage;
    return this;
  }

  public void unsetSolutionMessage() {
    this.solutionMessage = null;
  }

  /** Returns true if field solutionMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetSolutionMessage() {
    return this.solutionMessage != null;
  }

  public void setSolutionMessageIsSet(boolean value) {
    if (!value) {
      this.solutionMessage = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ERROR_MESSAGE:
      if (value == null) {
        unsetErrorMessage();
      } else {
        setErrorMessage((java.lang.String)value);
      }
      break;

    case SOLUTION_MESSAGE:
      if (value == null) {
        unsetSolutionMessage();
      } else {
        setSolutionMessage((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR_MESSAGE:
      return getErrorMessage();

    case SOLUTION_MESSAGE:
      return getSolutionMessage();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ERROR_MESSAGE:
      return isSetErrorMessage();
    case SOLUTION_MESSAGE:
      return isSetSolutionMessage();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Announcement)
      return this.equals((Announcement)that);
    return false;
  }

  public boolean equals(Announcement that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_errorMessage = true && this.isSetErrorMessage();
    boolean that_present_errorMessage = true && that.isSetErrorMessage();
    if (this_present_errorMessage || that_present_errorMessage) {
      if (!(this_present_errorMessage && that_present_errorMessage))
        return false;
      if (!this.errorMessage.equals(that.errorMessage))
        return false;
    }

    boolean this_present_solutionMessage = true && this.isSetSolutionMessage();
    boolean that_present_solutionMessage = true && that.isSetSolutionMessage();
    if (this_present_solutionMessage || that_present_solutionMessage) {
      if (!(this_present_solutionMessage && that_present_solutionMessage))
        return false;
      if (!this.solutionMessage.equals(that.solutionMessage))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetErrorMessage()) ? 131071 : 524287);
    if (isSetErrorMessage())
      hashCode = hashCode * 8191 + errorMessage.hashCode();

    hashCode = hashCode * 8191 + ((isSetSolutionMessage()) ? 131071 : 524287);
    if (isSetSolutionMessage())
      hashCode = hashCode * 8191 + solutionMessage.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Announcement other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetErrorMessage()).compareTo(other.isSetErrorMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrorMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errorMessage, other.errorMessage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSolutionMessage()).compareTo(other.isSetSolutionMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSolutionMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.solutionMessage, other.solutionMessage);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Announcement(");
    boolean first = true;

    if (isSetErrorMessage()) {
      sb.append("errorMessage:");
      if (this.errorMessage == null) {
        sb.append("null");
      } else {
        sb.append(this.errorMessage);
      }
      first = false;
    }
    if (isSetSolutionMessage()) {
      if (!first) sb.append(", ");
      sb.append("solutionMessage:");
      if (this.solutionMessage == null) {
        sb.append("null");
      } else {
        sb.append(this.solutionMessage);
      }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class AnnouncementStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AnnouncementStandardScheme getScheme() {
      return new AnnouncementStandardScheme();
    }
  }

  private static class AnnouncementStandardScheme extends org.apache.thrift.scheme.StandardScheme<Announcement> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Announcement struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERROR_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.errorMessage = iprot.readString();
              struct.setErrorMessageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SOLUTION_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.solutionMessage = iprot.readString();
              struct.setSolutionMessageIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Announcement struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.errorMessage != null) {
        if (struct.isSetErrorMessage()) {
          oprot.writeFieldBegin(ERROR_MESSAGE_FIELD_DESC);
          oprot.writeString(struct.errorMessage);
          oprot.writeFieldEnd();
        }
      }
      if (struct.solutionMessage != null) {
        if (struct.isSetSolutionMessage()) {
          oprot.writeFieldBegin(SOLUTION_MESSAGE_FIELD_DESC);
          oprot.writeString(struct.solutionMessage);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AnnouncementTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public AnnouncementTupleScheme getScheme() {
      return new AnnouncementTupleScheme();
    }
  }

  private static class AnnouncementTupleScheme extends org.apache.thrift.scheme.TupleScheme<Announcement> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Announcement struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetErrorMessage()) {
        optionals.set(0);
      }
      if (struct.isSetSolutionMessage()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetErrorMessage()) {
        oprot.writeString(struct.errorMessage);
      }
      if (struct.isSetSolutionMessage()) {
        oprot.writeString(struct.solutionMessage);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Announcement struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.errorMessage = iprot.readString();
        struct.setErrorMessageIsSet(true);
      }
      if (incoming.get(1)) {
        struct.solutionMessage = iprot.readString();
        struct.setSolutionMessageIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

