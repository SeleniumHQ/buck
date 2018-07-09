/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.rules.modern.builders.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class Tree implements org.apache.thrift.TBase<Tree, Tree._Fields>, java.io.Serializable, Cloneable, Comparable<Tree> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Tree");

  private static final org.apache.thrift.protocol.TField ROOT_FIELD_DESC = new org.apache.thrift.protocol.TField("root", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField CHILDREN_FIELD_DESC = new org.apache.thrift.protocol.TField("children", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TreeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TreeTupleSchemeFactory();

  public Directory root; // required
  public java.util.List<Directory> children; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROOT((short)1, "root"),
    CHILDREN((short)2, "children");

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
        case 1: // ROOT
          return ROOT;
        case 2: // CHILDREN
          return CHILDREN;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROOT, new org.apache.thrift.meta_data.FieldMetaData("root", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Directory.class)));
    tmpMap.put(_Fields.CHILDREN, new org.apache.thrift.meta_data.FieldMetaData("children", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Directory.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Tree.class, metaDataMap);
  }

  public Tree() {
  }

  public Tree(
    Directory root,
    java.util.List<Directory> children)
  {
    this();
    this.root = root;
    this.children = children;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Tree(Tree other) {
    if (other.isSetRoot()) {
      this.root = new Directory(other.root);
    }
    if (other.isSetChildren()) {
      java.util.List<Directory> __this__children = new java.util.ArrayList<Directory>(other.children.size());
      for (Directory other_element : other.children) {
        __this__children.add(new Directory(other_element));
      }
      this.children = __this__children;
    }
  }

  public Tree deepCopy() {
    return new Tree(this);
  }

  @Override
  public void clear() {
    this.root = null;
    this.children = null;
  }

  public Directory getRoot() {
    return this.root;
  }

  public Tree setRoot(Directory root) {
    this.root = root;
    return this;
  }

  public void unsetRoot() {
    this.root = null;
  }

  /** Returns true if field root is set (has been assigned a value) and false otherwise */
  public boolean isSetRoot() {
    return this.root != null;
  }

  public void setRootIsSet(boolean value) {
    if (!value) {
      this.root = null;
    }
  }

  public int getChildrenSize() {
    return (this.children == null) ? 0 : this.children.size();
  }

  public java.util.Iterator<Directory> getChildrenIterator() {
    return (this.children == null) ? null : this.children.iterator();
  }

  public void addToChildren(Directory elem) {
    if (this.children == null) {
      this.children = new java.util.ArrayList<Directory>();
    }
    this.children.add(elem);
  }

  public java.util.List<Directory> getChildren() {
    return this.children;
  }

  public Tree setChildren(java.util.List<Directory> children) {
    this.children = children;
    return this;
  }

  public void unsetChildren() {
    this.children = null;
  }

  /** Returns true if field children is set (has been assigned a value) and false otherwise */
  public boolean isSetChildren() {
    return this.children != null;
  }

  public void setChildrenIsSet(boolean value) {
    if (!value) {
      this.children = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROOT:
      if (value == null) {
        unsetRoot();
      } else {
        setRoot((Directory)value);
      }
      break;

    case CHILDREN:
      if (value == null) {
        unsetChildren();
      } else {
        setChildren((java.util.List<Directory>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROOT:
      return getRoot();

    case CHILDREN:
      return getChildren();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROOT:
      return isSetRoot();
    case CHILDREN:
      return isSetChildren();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Tree)
      return this.equals((Tree)that);
    return false;
  }

  public boolean equals(Tree that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_root = true && this.isSetRoot();
    boolean that_present_root = true && that.isSetRoot();
    if (this_present_root || that_present_root) {
      if (!(this_present_root && that_present_root))
        return false;
      if (!this.root.equals(that.root))
        return false;
    }

    boolean this_present_children = true && this.isSetChildren();
    boolean that_present_children = true && that.isSetChildren();
    if (this_present_children || that_present_children) {
      if (!(this_present_children && that_present_children))
        return false;
      if (!this.children.equals(that.children))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRoot()) ? 131071 : 524287);
    if (isSetRoot())
      hashCode = hashCode * 8191 + root.hashCode();

    hashCode = hashCode * 8191 + ((isSetChildren()) ? 131071 : 524287);
    if (isSetChildren())
      hashCode = hashCode * 8191 + children.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Tree other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRoot()).compareTo(other.isSetRoot());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoot()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.root, other.root);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetChildren()).compareTo(other.isSetChildren());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChildren()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.children, other.children);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Tree(");
    boolean first = true;

    sb.append("root:");
    if (this.root == null) {
      sb.append("null");
    } else {
      sb.append(this.root);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("children:");
    if (this.children == null) {
      sb.append("null");
    } else {
      sb.append(this.children);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (root != null) {
      root.validate();
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TreeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TreeStandardScheme getScheme() {
      return new TreeStandardScheme();
    }
  }

  private static class TreeStandardScheme extends org.apache.thrift.scheme.StandardScheme<Tree> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Tree struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROOT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.root = new Directory();
              struct.root.read(iprot);
              struct.setRootIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CHILDREN
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list24 = iprot.readListBegin();
                struct.children = new java.util.ArrayList<Directory>(_list24.size);
                Directory _elem25;
                for (int _i26 = 0; _i26 < _list24.size; ++_i26)
                {
                  _elem25 = new Directory();
                  _elem25.read(iprot);
                  struct.children.add(_elem25);
                }
                iprot.readListEnd();
              }
              struct.setChildrenIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Tree struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.root != null) {
        oprot.writeFieldBegin(ROOT_FIELD_DESC);
        struct.root.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.children != null) {
        oprot.writeFieldBegin(CHILDREN_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.children.size()));
          for (Directory _iter27 : struct.children)
          {
            _iter27.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TreeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TreeTupleScheme getScheme() {
      return new TreeTupleScheme();
    }
  }

  private static class TreeTupleScheme extends org.apache.thrift.scheme.TupleScheme<Tree> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Tree struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRoot()) {
        optionals.set(0);
      }
      if (struct.isSetChildren()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetRoot()) {
        struct.root.write(oprot);
      }
      if (struct.isSetChildren()) {
        {
          oprot.writeI32(struct.children.size());
          for (Directory _iter28 : struct.children)
          {
            _iter28.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Tree struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.root = new Directory();
        struct.root.read(iprot);
        struct.setRootIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list29 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.children = new java.util.ArrayList<Directory>(_list29.size);
          Directory _elem30;
          for (int _i31 = 0; _i31 < _list29.size; ++_i31)
          {
            _elem30 = new Directory();
            _elem30.read(iprot);
            struct.children.add(_elem30);
          }
        }
        struct.setChildrenIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
