CXX = g++
LIBS := -llunaservice  -lmojoluna -lmojodb -lmojocore -lpurple -lsanitize
LOCAL_LDFLAGS := -Wl,-Rlib/armv7 -Llib/armv7 $(LDFLAGS) $(LIBS) -Wl,-R/media/cryptofs/apps/usr/palm/applications/org.webosinternals.messaging/sysfiles/usr/lib

IM_SOURCES := \
	BuddyListConsolidator.cpp \
	IMMessage.cpp \
	IMServiceHandler.cpp \
	IMServiceApp.cpp \
	LibpurpleAdapter.cpp \
	LibpurpleAdapterPrefs.cpp \
	IMLoginState.cpp \
	IncomingIMHandler.cpp \
	OutgoingIMHandler.cpp \
	ConnectionStateHandler.cpp \
	DisplayController.cpp \
	SendOneMessageHandler.cpp \
	OutgoingIMCommandHandler.cpp \
	SendOneCommandHandler.cpp \
	OnEnabledHandler.cpp \
	BuddyStatusHandler.cpp
	
SOURCE_DIRS := src

IM_TARGET := $(OBJDIR)/org.webosinternals.imlibpurpletransport
IM_OBJECTS := $(IM_SOURCES:%.cpp=$(OBJDIR)/%.o)

all: setup $(IM_TARGET)
		
$(IM_TARGET): $(IM_OBJECTS) 
	$(CXX) -o $(IM_TARGET) $(IM_OBJECTS) $(LOCAL_LDFLAGS) lib/armv7/libstdc++.so.6
	
$(OBJDIR)/%.o: %.cpp
	$(CXX) -MMD $(INCLUDES) $(LOCAL_CFLAGS) $(LOCAL_CPPFLAGS) -c $< -o $@

setup: $(OBJDIR)

$(OBJDIR):
	@mkdir -p $(OBJDIR)

vpath %.cpp $(SOURCE_DIRS)

-include $(IM_SOURCES:%.cpp=$(OBJDIR)/%.d)
	
clean:
	rm -rf $(OBJDIR)
	rm -rf doc/html
	
.PHONY: clean all setup docs
