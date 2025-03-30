package calsim.surrogate;

/**
 * Represents a mapping between an external time series file column and a surrogate (ANN) input.
 * Textual identifiers are used and then resolved to numeric indexes at runtime.
 */
public class ExogTimeSeriesAssignment {
    // The constant used when an assignment is absent.
    public static final ExogTimeSeriesAssignment UNASSIGNED =
        new ExogTimeSeriesAssignment("default", "default", "default");

    private String fileName;
    private String fileColumn;
    private int fileColumnIndex;  // Resolved via ExogenousTimeSeries API (-1 if unresolved)
    private int annInputIndex;    // Resolved via the surrogate API (-1 if unresolved)
    private String annInputName;

    /**
     * Constructs an assignment using textual identifiers. If any argument is null, the assignment
     * is replaced with UNASSIGNED.
     */
    public ExogTimeSeriesAssignment(String fileName, String fileColumn, String annInputName) {
        if (fileName == null || fileColumn == null || annInputName == null) {
            // Replace any null assignment with UNASSIGNED.
            this.fileName = UNASSIGNED.fileName;
            this.fileColumn = UNASSIGNED.fileColumn;
            this.annInputName = UNASSIGNED.annInputName;
            this.fileColumnIndex = -1;
            this.annInputIndex = -1;
        } else {
            this.fileName = fileName;
            this.fileColumn = fileColumn;
            this.annInputName = annInputName;
            this.fileColumnIndex = -1;
            this.annInputIndex = -1;
        }
    }

    
    /**
     * Copy constructor copies application data but not internals. This class is not
     * intended for re-use across surrogates.
     * @param other
     */
    public ExogTimeSeriesAssignment(ExogTimeSeriesAssignment other) {
        this.fileName = other.fileName;
        this.fileColumn = other.fileColumn;
        this.annInputName = other.annInputName;
        this.fileColumnIndex = other.fileColumnIndex;
        this.annInputIndex = other.annInputIndex;
    }    
    
    // Optionally, a full constructor is provided.
    public ExogTimeSeriesAssignment(String fileName, String fileColumn, int fileColumnIndex, int annInputIndex, String annInputName) {
        this.fileName = fileName;
        this.fileColumn = fileColumn;
        this.fileColumnIndex = fileColumnIndex;
        this.annInputIndex = annInputIndex;
        this.annInputName = annInputName;
    }

    
    public boolean isUnassigned() {
        return "default".equals(this.annInputName);
    }        
    
    // Getters and setters
    public String getFileName() {
        return fileName;
    }

    public String getFileColumn() {
        return fileColumn;
    }

    public int getFileColumnIndex() {
        return fileColumnIndex;
    }

    public void setFileColumnIndex(int fileColumnIndex) {
        this.fileColumnIndex = fileColumnIndex;
    }

    public int getAnnInputIndex() {
        return annInputIndex;
    }

    public void setAnnInputIndex(int annInputIndex) {
        if (this.annInputIndex >= 0) {
            throw new IllegalStateException("annInputIndex already set. This time series assignment may have been reused.");
        }
        this.annInputIndex = annInputIndex;
    }

    public String getAnnInputName() {
        return annInputName;
    }

    public void setAnnInputName(String annInputName) {
        this.annInputName = annInputName;
    }

    /**
     * Returns true if both the file column and ANN input have been resolved.
     */
    public boolean isAssigned() {
        return this.fileColumnIndex >= 0 && this.annInputIndex >= 0;
    }

    @Override
    public String toString() {
        return "ExogTimeSeriesAssignment [fileName=" + fileName 
            + ", fileColumn=" + fileColumn 
            + ", fileColumnIndex=" + fileColumnIndex 
            + ", annInputIndex=" + annInputIndex 
            + ", annInputName=" + annInputName + "]";
    }
}
